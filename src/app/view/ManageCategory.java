package app.view;

import app.config.Config;
import app.config.ValidateConfig;
import app.controller.CategoryController;
import app.model.Category;

import java.util.List;

public class ManageCategory {
    CategoryController categoryController = new CategoryController();
    List<Category> categoryList = categoryController.getListCategory();
    public void manageCategory(){
        System.out.println("*-------------------------------------------------------------------------------*");
        System.out.println("|                          QUẢN LÝ DANH MỤC                                     |");
        System.out.println("|-------------------------------------------------------------------------------|");
        System.out.println("| 1. Hiện toàn bộ danh mục.                                                     |");
        System.out.println("| 2. Thêm mới danh mục.                                                         |");
        System.out.println("| 3. Cập nhật danh mục.                                                         |");
        System.out.println("| 4. Xoá danh mục.                                                              |");
        System.out.println("| 9. Quay lại.                                                                  |");
        System.out.println("*-------------------------------------------------------------------------------*");
        System.out.print("Nhập vào lựa chọn của bạn: ");
        int choice = Config.getInteger();
        switch (choice){
            case 1:
                showListCatelory();
                break;
            case 2:
                createCategory();
                break;
            case 3:
                updateCategory();
                break;
            case 4:
                deleteCategory();
                break;
            case 9:
                new AdminView();
                break;
            default:
                System.out.println("Không hợp lệ!!!");
                new AdminView();
        }
        new AdminView();
    }



    private void showListCatelory() {
        if (categoryList.size()==0){
            System.out.println("Danh sách trống. Vui lòng thêm mới!!!");
        }else {
            for (int i = 0; i < categoryList.size(); i++) {
                categoryList.get(i).displayData();
            }
        }
        System.out.println("Ấn phím bất kỳ để tiếp tục hoặc ấn Back để quay lại!!!");
        String backMenu = Config.getString();
        if (backMenu.equalsIgnoreCase("back")) {
            manageCategory();
        }
    }
    private void createCategory() {
        while (true) {
            try {
                int id = 0;
                if (categoryList.size() == 0) {
                    id = 1;
                } else {
                    id = categoryList.get(categoryList.size() - 1).getCategoryId() + 1;
                }
                String categoryName;
                while (true) {
                    System.out.print("Nhập danh mục Film : ");
                    categoryName = Config.getString();
                    if (!ValidateConfig.validateName(categoryName)){
                        System.out.println("Tên không hợp lệ. Vui lòng nhập lại!!! ");

                    }else {
                        break;
                    }
                }
                categoryController.createCategory(new Category(id,categoryName));
                System.out.println("Thêm vào thành công!!!");
                System.out.println("Ấn phím bất kỳ để tiếp tục hoặc ấn Back để quay lại!!!");
                String backMenu = Config.getString();
                if (backMenu.equalsIgnoreCase("back")) {
                    manageCategory();
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private void updateCategory() {
        while (true) {
            System.out.println("Nhập vào ID muốn cập nhật :  ");
            int id = Config.getInteger();
            if (categoryController.detailCategory(id) == null) {
                System.out.println("ID không hợp lệ!!!");
            } else {
                System.out.print("NHập tên Film : ");
                String name = Config.getString();

                categoryController.createCategory(new Category(id, name));
                System.out.println("Cập nhật thành công!!!");
                System.out.println("Ấn phím bất kỳ để tiếp tục hoặc ấn Back để quay lại!!!");
                String backMenu = Config.getString();
                if (backMenu.equalsIgnoreCase("back")) {
                    manageCategory();
                    break;
                }
            }
        }
    }
    private void deleteCategory() {
        while (true) {
            System.out.println("Nhập vào ID muốn xoá : ");
            int deleteId = Config.getInteger();
            if (categoryController.detailCategory(deleteId) == null) {
                System.out.println("ID không hợp lệ!!!");
            } else {
                categoryController.deleteCategory(deleteId);
                System.out.println("Xoá thành công!!!");
                System.out.println("Nhấn phím bất kì để quay lại!!!");
                Config.getString();
                manageCategory();
                break;
            }
        }
    }
}
