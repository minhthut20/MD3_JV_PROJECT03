package app.controller;

import app.model.Category;
import app.model.Film;
import app.service.category.CategoryServiceIPLM;

import java.util.List;

public class CategoryController {
    CategoryServiceIPLM categoryServiceIPLM = new CategoryServiceIPLM();
    public List<Category> getListCategory(){
        return  categoryServiceIPLM.findAll();
    }

    public void createCategory(Category category){
        categoryServiceIPLM.save(category);
    }
    public Category detailCategory(int id){
        return categoryServiceIPLM.findById(id);
    }
    public void deleteCategory(int deleteId) {
        categoryServiceIPLM.delete(deleteId);
    }
}
