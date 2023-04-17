package app.view;

import app.config.Config;
import app.controller.CategoryController;
import app.controller.FilmController;
import app.controller.VideoController;
import app.model.Category;
import app.model.Film;
import app.model.Video;

import java.util.ArrayList;
import java.util.List;


public class ManageFilm {
    FilmController filmController = new FilmController();

    VideoController videoController = new VideoController();


    public void manageFilm() {
        System.out.println("*-------------------------------------------------------------------------------*");
        System.out.println("|                          QUẢN LÝ FILM                                         |");
        System.out.println("|-------------------------------------------------------------------------------|");
        System.out.println("| 1. Hiện toàn bộ Film.                                                         |");
        System.out.println("| 2. Thêm mới Film.                                                             |");
        System.out.println("| 3. Cập nhật Film.                                                             |");
        System.out.println("| 4. Xoá Film.                                                                  |");
        System.out.println("| 9. Quay lại.                                                                  |");
        System.out.println("*-------------------------------------------------------------------------------*");
        System.out.print("Nhập vào lựa chọn của bạn: ");
        int choice = Config.getInteger();
        switch (choice) {
            case 1:
                showListFilm();
                break;
            case 2:
                createFilm();
                break;
            case 3:
                updateFilm();
                break;
            case 4:
                deleteFilm();
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

    public void showListFilm() {
        List<Film> filmList = filmController.getListFilm();
        if (filmList.size() == 0) {
            System.out.println("Danh sách trống. Vui lòng thêm mới!!!");
        } else {
            for (int i = 0; i < filmList.size(); i++) {
                filmList.get(i).displayData();
            }
        }
        System.out.println("Ấn phím bất kỳ để tiếp tục hoặc ấn Back để quay lại!!!");
        String backMenu = Config.getString();
        if (backMenu.equalsIgnoreCase("back")) {
            manageFilm();
        }
    }

    public void createFilm() {
        while (true) {
            try {
                List<Film> filmList = filmController.getListFilm();
                int id = 0;
                if (filmList.size() == 0) {
                    id = 1;
                } else {
                    id = filmList.get(filmList.size() - 1).getFilmId() + 1;
                }
                System.out.print("NHập tên Film : ");
                String name = Config.getString();
                System.out.print("Mô tả Film : ");
                String description = Config.getString();

                Category category = null;
                while (true) {
                    //in dah muc hien co
                    List<Category> categoryList = new CategoryController().getListCategory();
                    for (int i = 0; i < categoryList.size(); i++) {
                        System.out.println("id: " + categoryList.get(i).getCategoryId() + ", tên: " + categoryList.get(i).getCategoryName());
                    }
                    //chon danh muc
                    System.out.println("Chọn 1 danh mục : ");
                    int fimlId = Config.getInteger();
                    for (int i = 0; i < categoryList.size(); i++) {
                        if (fimlId == categoryList.get(i).getCategoryId()) {
                            category = categoryList.get(i);
                            System.out.println("Chọn danh mục thành công!");
                            break;
                        }
                    }
                    if (category == null) {
                        System.out.println("Lựa chọn không hợp lệ!!!");
                    } else {
                        break;
                    }
                }

                System.out.print("Năm phát hành : ");
                String releasedYear = Config.getString();
                System.out.print("Quốc gia : ");
                String nation = Config.getString();
                List<Video> episodeList = new ArrayList<>();
                while (true) {
                    System.out.println("Chọn video cho Film:");
                    List<Video> videoList = videoController.findVideoByCategory(category);
                    if (videoList.size() == 0) {
                        System.out.println("Không có video hợp lệ!!!");
                        break;
                    }
                    for (int i = 0; i < videoList.size(); i++) {
                        videoList.get(i).displayData();
                    }

                    while (true) {
                        System.out.println("Chọn ID video: ");
                        int videoId = Config.getInteger();
                        int indexVideo = -1;
                        for (int i = 0; i < videoList.size(); i++) {
                            if (videoList.get(i).getVideoId() == videoId) {
                                indexVideo = i;
                            }
                        }
                        if (indexVideo == -1) {
                            System.out.println("Lựa chọn không hợp lệ!!! Vui lòng chọn lại.");
                        } else {
                            boolean existVideo = false;
                            for (int i = 0; i < episodeList.size(); i++) {
                                if (episodeList.get(i).getVideoId() == videoId) {
                                    existVideo = true;
                                }
                            }
                            if (existVideo) {
                                System.out.println("Video đã có trong List. Vui lòng thử lại !!");
                                System.out.println("Ấn phím bất kỳ để tiếp tục hoặc ấn Back để thoát!!!");
                                String backMenu = Config.getString();
                                if (backMenu.equalsIgnoreCase("back")) {
                                    break;
                                }
                            } else {
                                episodeList.add(videoList.get(indexVideo));
                                System.out.println("Thêm video thành công!");
                                System.out.println("Ấn phím bất kỳ để tiếp tục hoặc ấn Back để quay lại!!!");
                                String backMenu = Config.getString();
                                if (backMenu.equalsIgnoreCase("back")) {
                                    break;
                                }
                            }
                        }
                    }
                    break;
                }


                System.out.print("Tình trạng : ");
                boolean status = Config.getBoolean();
                filmController.addFilm(new Film(id, name, description, category, releasedYear, nation, status, episodeList));

                System.out.println("Thêm vào thành công!!!");
                System.out.println("Ấn phím bất kỳ để tiếp tục hoặc ấn Back để quay lại!!!");
                String backMenu = Config.getString();
                if (backMenu.equalsIgnoreCase("back")) {
                    manageFilm();
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void updateFilm() {
        while (true) {
            System.out.println("Nhập vào ID muốn cập nhật :  ");
            int id = Config.getInteger();

            Film currentFilm = filmController.detailFilm(id);
            if (currentFilm == null) {
                System.out.println("ID không hợp lệ!!!");

            } else {
                System.out.print("NHập tên Film : ");
                currentFilm.setFilmName(Config.getString());
                System.out.print("Mô tả Film : ");
                currentFilm.setDescription(Config.getString());
                Category category = currentFilm.getCategory();
                int categoryCurentId = category.getCategoryId();
                while (true) {
                    ///in dah muc hien co
                    List<Category> categoryList = new CategoryController().getListCategory();
                    for (int i = 0; i < categoryList.size(); i++) {
                        System.out.println("id: " + categoryList.get(i).getCategoryId() + ", tên: " + categoryList.get(i).getCategoryName());
                    }
                    //chon danh muc
                    System.out.println("Chọn 1 danh mục : ");
                    int fimlId = Config.getInteger();
                    Boolean flag = false;
                    for (int i = 0; i < categoryList.size(); i++) {
                        if (fimlId == categoryList.get(i).getCategoryId()) {
                            currentFilm.setCategory(categoryList.get(i));
                            category = categoryList.get(i);
                            flag = true;
                        }
                    }
                    if (flag) {
                        System.out.println("Cập nhật thành công");
                        break;
                    } else {
                        System.out.println("Lựa chọn không hợp lệ!!!");
                        break;
                    }
                }

                System.out.print("Năm phát hành : ");
                currentFilm.setReleasedYear(Config.getString());
                System.out.print("Quốc gia : ");
                currentFilm.setNation(Config.getString());
                List<Video> episodeList = currentFilm.getEpisodeList();
                if (categoryCurentId!=category.getCategoryId()){
                    System.out.println("Danh mục đã thay đổi. Vui lòng chọn lại toàn bộ Video !!!");
                    episodeList=new ArrayList<>();
                }
                while (true) {
                    System.out.println("Chọn video cho Film:");
                    List<Video> videoList = videoController.findVideoByCategory(category);
                    if (videoList.size() == 0) {
                        System.out.println("Không có video hợp lệ!!!");
                        break;
                    }
                    for (int i = 0; i < videoList.size(); i++) {
                        videoList.get(i).displayData();
                    }

                    while (true) {
                        System.out.println("Chọn ID video: ");
                        int videoId = Config.getInteger();
                        int indexVideo = -1;
                        for (int i = 0; i < videoList.size(); i++) {
                            if (videoList.get(i).getVideoId() == videoId) {
                                indexVideo = i;
                            }
                        }
                        if (indexVideo == -1) {
                            System.out.println("Lựa chọn không hợp lệ!!! Vui lòng chọn lại.");
                        } else {
                            boolean existVideo = false;
                            for (int i = 0; i < episodeList.size(); i++) {
                                if (episodeList.get(i).getVideoId() == videoId) {
                                    existVideo = true;
                                }
                            }
                            if (existVideo) {
                                System.out.println("Video đã có trong List. Vui lòng thử lại !!");
                                System.out.println("Ấn phím bất kỳ để tiếp tục hoặc ấn Back thoát!!!");
                                String backMenu = Config.getString();
                                if (backMenu.equalsIgnoreCase("back")) {
                                    break;
                                }
                            } else {
                                episodeList.add(videoList.get(indexVideo));
                                System.out.println("Thêm video thành công!");
                                System.out.println("Ấn phím bất kỳ để tiếp tục hoặc ấn Back để quay lại!!!");
                                String backMenu = Config.getString();
                                if (backMenu.equalsIgnoreCase("back")) {
                                    break;
                                }
                            }
                        }
                    }
                    break;
                }
                currentFilm.setEpisodeList(episodeList);
                System.out.print("Tình trạng : ");
                currentFilm.setStatus(Config.getBoolean());
                filmController.addFilm(currentFilm);
                System.out.println("Cập nhật thành công!!!");
                System.out.println("Ấn phím bất kỳ để tiếp tục hoặc ấn Back để quay lại!!!");
                String backMenu = Config.getString();
                if (backMenu.equalsIgnoreCase("back")) {
                    manageFilm();
                    break;
                }
            }
            List<Film> filmList = filmController.getListFilm();
        }
    }


    public void deleteFilm() {
        while (true) {
            System.out.println("Nhập vào ID muốn xoá : ");
            int deleteId = Config.getInteger();
            if (filmController.detailFilm(deleteId) == null) {
                System.out.println("ID không hợp lệ!!!");
            } else {
                filmController.deleteById(deleteId);
                System.out.println("Xoá thành công!!!");
                System.out.println("Nhấn phím bất kì để quay lại!!!");
                Config.getString();
                manageFilm();
                break;

            }
        }
    }
}
