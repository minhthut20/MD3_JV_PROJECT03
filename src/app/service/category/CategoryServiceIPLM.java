package app.service.category;

import app.config.Config;
import app.model.Category;
import app.model.Film;
import database.DataBase;

import java.util.List;

public class CategoryServiceIPLM implements ICategoryService{
    List<Category> categoryList = new Config<Category>().readFromFile(DataBase.PATH_CATEGORY);
    @Override
    public List<Category> findAll() {
        return categoryList;
    }

    @Override
    public void save(Category category) {
        Category category1 = findById(category.getCategoryId());
        if (category1 ==null){
            categoryList.add(category);
        }else {
            int index = categoryList.indexOf(category1);
            categoryList.set(index,category);
        }
        new Config<Category>().writeFile(DataBase.PATH_CATEGORY,categoryList );
    }

    @Override
    public Category findById(int id) {
        for (int i = 0; i < categoryList.size(); i++) {
            Category category = categoryList.get(i);
            if (category.getCategoryId()==id){
                return category;
            }
        }
        return null;
    }

    @Override
    public void delete(int id) {
        categoryList.remove(findById(id));
        new Config<Category>().writeFile(DataBase.PATH_CATEGORY,categoryList);
    }


}
