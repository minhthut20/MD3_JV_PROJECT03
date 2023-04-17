package app.view;

import app.config.Config;
import app.config.ValidateConfig;
import app.controller.CategoryController;
import app.controller.VideoController;
import app.model.Category;
import app.model.Video;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class ManageVideo {
    VideoController videoController = new VideoController();
    List<Video> videoList = videoController.getListVideo();
    public void manageVideo(){
        System.out.println("*-------------------------------------------------------------------------------*");
        System.out.println("|                          QUẢN LÝ VIDEO                                        |");
        System.out.println("|-------------------------------------------------------------------------------|");
        System.out.println("| 1. Hiện toàn bộ video.                                                        |");
        System.out.println("| 2. Thêm mới video.                                                            |");
        System.out.println("| 3. Cập nhật video.                                                            |");
        System.out.println("| 4. Xoá video.                                                                 |");
        System.out.println("| 9. Quay lại.                                                                  |");
        System.out.println("*-------------------------------------------------------------------------------*");
        System.out.print("Nhập vào lựa chọn của bạn: ");
        int choice = Config.getInteger();
        switch (choice){
            case 1:
                showListVideo();
                break;
            case 2:
                createVideo();
                break;
            case 3:
                updateVideo();
                break;
            case 4:
                deleteVideo();
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

    private void showListVideo() {
        if (videoList.size()==0){
            System.out.println("Danh sách trống. Vui lòng thêm mới!!!");
        }else {
            for (int i = 0; i < videoList.size(); i++) {
                videoList.get(i).displayData();
            }
        }
        System.out.println("Ấn phím bất kỳ để tiếp tục hoặc ấn Back để quay lại!!!");
        String backMenu = Config.getString();
        if (backMenu.equalsIgnoreCase("back")) {
            manageVideo();
        }
    }

    private void createVideo() {
        while (true) {
            try {
                int id = 0;
                if (videoList.size() == 0) {
                    id = 1;
                } else {
                    id = videoList.get(videoList.size() - 1).getVideoId() + 1;
                }
                String videoName;
                while (true) {
                    System.out.print("Nhập tên Video : ");
                    videoName = Config.getString();
                    if (!ValidateConfig.validateName(videoName)){
                        System.out.println("Tên không hợp lệ. Vui lòng nhập lại!!! ");

                    }else {
                        break;
                    }
                }
                String videoDate;
                System.out.println("Nhập năm phát hành : ");
                videoDate = Config.getString();
                Category category = null;
                while (true){
                    //in dah muc hien co
                    List<Category> categoryList = new CategoryController().getListCategory();
                    for (int i = 0; i < categoryList.size(); i++) {
                        System.out.println("id: " + categoryList.get(i).getCategoryId() + ", tên: " +categoryList.get(i).getCategoryName());
                    }
                    //chon danh muc
                    System.out.println("Chọn 1 danh mục : ");
                    int categoryId = Config.getInteger();
                    for (int i = 0; i < categoryList.size(); i++) {
                        if (categoryId==categoryList.get(i).getCategoryId()){
                            category = categoryList.get(i);
                        }
                    }
                    if (category==null){
                        System.out.println("Lựa chọn không hợp lệ!!!");
                    }else {
                        break;
                    }
                }
                videoController.createVideo(new Video(id, videoName,videoDate,category));
                System.out.println("Thêm vào thành công!!!");
                System.out.println("Ấn phím bất kỳ để tiếp tục hoặc ấn Back để quay lại!!!");
                String backMenu = Config.getString();
                if (backMenu.equalsIgnoreCase("back")) {
                    manageVideo();
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void updateVideo() {
        while (true) {
            Category category = null;
            System.out.println("Nhập vào ID video muốn cập nhật :  ");
            int id = Config.getInteger();
            Video video = videoController.detailVideo(id);
            if (video == null) {
                System.out.println("ID không hợp lệ!!!");
            } else {
                System.out.print("Nhập tên Video : ");
                video.setVideoName(Config.getString());
                System.out.print("Năm phát hành : ");
                video.setVideoDate(Config.getString());
                while (true){
                    //in dah muc hien co
                    List<Category> categoryList = new CategoryController().getListCategory();

                    for (int i = 0; i < categoryList.size(); i++) {
                        System.out.println("id: " + categoryList.get(i).getCategoryId() + ", tên: " +categoryList.get(i).getCategoryName());
                    }
                    //chon danh muc
                    System.out.println("Chọn 1 danh mục : ");
                    int categoryId = Config.getInteger();
                    for (int i = 0; i < categoryList.size(); i++) {
                        if (categoryId==categoryList.get(i).getCategoryId()){
                            category = categoryList.get(i);
                        }
                    }
                    if (category==null){
                        System.out.println("Lựa chọn không hợp lệ!!!");
                    }else {
                        break;
                    }
                }
                video.setCategory(category);
                videoController.update(video);
                System.out.println("Cập nhật thành công!!!");
                System.out.println("Ấn phím bất kỳ để tiếp tục hoặc ấn Back để quay lại!!!");
                String backMenu = Config.getString();
                if (backMenu.equalsIgnoreCase("back")) {
                    manageVideo();
                    break;
                }
            }
        }
    }

    private void deleteVideo() {
        while (true) {
            System.out.println("Nhập vào ID video muốn xoá : ");
            int deleteId = Config.getInteger();
            if (videoController.detailVideo(deleteId) == null) {
                System.out.println("ID không hợp lệ!!!");
            } else {
                videoController.deleteById(deleteId);
                System.out.println("Xoá thành công!!!");
                System.out.println("Nhấn phím bất kì để quay lại!!!");
                Config.getString();
                manageVideo();
                break;
            }
        }
    }
}
