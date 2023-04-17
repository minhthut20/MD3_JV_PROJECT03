package app.model;

import java.io.Serializable;

public class Category implements Serializable {
    private int categoryId;
    private String categoryName;

    public Category() {
    }

    public Category(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void displayData(){
        System.out.println("*-------------------------------------------------------------------------------*");
        System.out.println("|                          THÔNG TIN DANH MỤC                                   |");
        System.out.println("*-------------------------------------------------------------------------------*");
        System.out.println("|                                                                               |");
        System.out.printf("|        ID: %-20s           Tên : %-30s|\n",String.valueOf(categoryId),categoryName);
        System.out.println("|                                                                               |");
        System.out.println("*-------------------------------------------------------------------------------*");
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
