package app.view;

import app.InitData.InitData;
import app.config.Config;
import app.controller.CategoryController;
import app.controller.FilmController;
import app.controller.UserController;
import app.model.*;

import java.util.List;
import java.util.Set;

public class NarBar {
    static FilmController filmController = new FilmController();
    static UserController userController = new UserController();
    static User loginUser;

    public NarBar() {
        loginUser = userController.getUserLogin();
        System.out.println("*-------------------------------------------------------------------------------*");
        System.out.println("|                                  MOTPHIMCHILLI                                |");
        System.out.println("|-------------------------------------------------------------------------------|");
        if (loginUser == null) {
            System.out.println("|  1. Đăng kí                                                                   |");
            System.out.println("|  2. Đăng nhập                                                                 |");
        } else {
            System.out.printf("|                                  WELCOME %-37s|\n" +
                    "|                                                                               |\n", loginUser.getName());
            System.out.println("|  1. Đăng xuất                                                                 |");
            System.out.println("|  2. Thông tin cá nhân                                                         |");
        }
        System.out.println("|  3. Hiển thị Film theo danh mục                                               |");
        System.out.println("|  4. Danh sách phim bộ                                                         |");
        System.out.println("|  5. Danh sách phim lẻ                                                         |");
        System.out.println("|  6. Hiển thị toàn bộ Film                                                     |");
        System.out.println("|  7. Top 3 Film xem nhiều nhất                                                 |");
        System.out.println("|  8. Top 3 Fim được yêu thích nhất                                             |");
        System.out.println("|  9. Tìm kiếm Film                                                             |");
        System.out.println("|-------------------------------------------------------------------------------|");
        System.out.println("|  0. Thoát                                                                     |");
        System.out.println("*-------------------------------------------------------------------------------*");
        System.out.print("Nhập vào lựa chọn của bạn : ");
        int choice = Config.getInteger();
        switch (choice) {
            case 1:
                if (loginUser == null) {
                    new UserView().formRegister();
                } else {
                    new UserView().signOut();
                }
                break;
            case 2:
                if (loginUser == null) {
                    new UserView().formLogin();
                } else {
                    new UserView().showUserInfo();
                }
                break;
            case 3:
                showListCategory();
                break;
            case 4:
                seriesFilm();
                break;
            case 5:
                singleFilm();
                break;
            case 6:
                showAllFilm();
                break;
            case 7:
                topViewFilm();
                break;
            case 8:
                topLikeFilm();
                break;
            case 9:
                findFilm();
                break;
            case 0:
                System.exit(0);
                break;
        }
    }

    private void topLikeFilm() {
        List<Film> topLike = filmController.getSortByLike();
        for (int i = 0; i < 3 && topLike.size()>i; i++) {
            topLike.get(i).displayData();
        }
        showFilmDetail(topLike);
    }

    private void topViewFilm() {
        List<Film> topView = filmController.getSortByView();
        for (int i = 0; i < 3 && topView.size()>i; i++) {
            topView.get(i).displayData();
        }
        showFilmDetail(topView);
    }

    private void showAllFilm() {
        List<Film> allFilmList = filmController.getListFilm();
        if (allFilmList.size()==0){
            System.out.println("Không có Film nào cả");
        }else {
            for (Film film:allFilmList) {
                film.displayData();
            }
        }
        showFilmDetail(allFilmList);
    }

    private void singleFilm() {
        List<Film> singleFilmList = filmController.getSingleFilmList();
        if (singleFilmList.size()==0){
            System.out.println("Không có film lẻ nào!");
        }else {
            for (Film film: singleFilmList) {
                film.displayData();
            }
        }
        showFilmDetail(singleFilmList);
    }

    private void seriesFilm() {
        List<Film> seriesFilmList = filmController.getSeriesFilmList();
        if (seriesFilmList.size()==0){
            System.out.println("Không có film bộ nào!");
        }else {
            for (Film film: seriesFilmList) {
                film.displayData();
            }
        }
        showFilmDetail(seriesFilmList);
    }

    private void showListCategory() {
        Category category = null;
        while (true) {
            //in dah muc hien co
            List<Category> categoryList = new CategoryController().getListCategory();
            for (int i = 0; i < categoryList.size(); i++) {
                System.out.println("id: " + categoryList.get(i).getCategoryId() + ", tên: " + categoryList.get(i).getCategoryName());
            }
            //chon danh muc
            System.out.println("Chọn 1 danh mục : ");
            int categoryId = Config.getInteger();
            for (int i = 0; i < categoryList.size(); i++) {
                if (categoryId == categoryList.get(i).getCategoryId()) {
                    category = categoryList.get(i);
                }
            }
            if (category == null) {
                System.out.println("Lựa chọn không hợp lệ!!!");
            } else {
                break;
            }
        }
        List<Film> filmList = filmController.findByCategory(category.getCategoryName());
        if (filmList.size() == 0) {
            System.out.println("Không có film nào trong danh mục này.");
            System.out.println("Nhấn phím bất kì để quay lại");
            Config.getString();
            new NarBar();
        } else {
            for (int i = 0; i < filmList.size(); i++) {
                filmList.get(i).displayData();
            }
            showFilmDetail(filmList);
        }
    }

    private static void showFilmDetail(List<Film> filmList) {

        System.out.println("Nhấn Next để chọn phim muốn xem hoặc Back để quay lại");
        String selection = Config.getString();
        if (selection.equalsIgnoreCase("Back")){
            new NarBar();
        } else if (selection.equalsIgnoreCase("Next")) {
            System.out.println("Nhập ID để chọn phim muốn xem chi tiết :");
            int id = Config.getInteger();
            viewFilmInfo(id,filmList);
        }else {
            System.out.println("Lựa chọn không hợp lệ!");
            showFilmDetail(filmList);
        }
    }

    private static void viewFilmInfo(int id, List<Film> filmList) {
        Film film = null;
        for (int i = 0; i < filmList.size(); i++) {
            if (filmList.get(i).getFilmId()==id){
                film=filmList.get(i);
                break;
            }
        }
        if (film==null){
            System.out.println("Không có Film hợp lệ!");
            System.out.println("Nhấn phím bất kì để tiếp tục tìm kiếm hoặc nhấn Back để quay lại!!!");
            String confirmAction = Config.getString();
            if (confirmAction.equalsIgnoreCase("Back")){
                new NarBar();
            }else {
                showFilmDetail(filmList);
            }
        }else {
            watchAndLikeFilm(filmList, film);
        }
    }

    private static void watchAndLikeFilm(List<Film> filmList, Film film) {
        System.out.println("Kết quả hợp lệ ");
        film.displayData();
        List<View> viewList = film.getViewFilmList();
        List<Like> likeList = film.getLikeFilmList();
        boolean likedFilm = false;
        Like likedUser = null;
        if (loginUser!=null){
            for (int i = 0; i < likeList.size(); i++) {
                if (likeList.get(i).getLikeUser()!=null){
                    if (likeList.get(i).getLikeUser().getId()==loginUser.getId()){
                        likedUser = likeList.get(i);
                        likedFilm = true;
                        break;
                    }
                }
            }
        }
        String likeStatusView=likedFilm?"UnLike":"Like";
        System.out.println("Bạn muốn \"xem\" hay \""+ likeStatusView + "\": ");
        String filmAction = Config.getString();
        if (filmAction.equalsIgnoreCase("xem")){
            System.out.println("........................Film: " + film.getFilmName()+" đang phát .................");
            int viewId =1;
            if (viewList.size()!=0){
                View lastView = viewList.get(viewList.size()-1);
                viewId=lastView.getViewId()+1;
            }
            View newView = new View(viewId,loginUser);
            viewList.add(newView);
            filmController.watchFilm(film);
            System.out.println("Nhấn phím bất kì để thoát");
            Config.getString();
            new NarBar();
        }else if (filmAction.equalsIgnoreCase("like")|| filmAction.equalsIgnoreCase("UnLIke")){
            if (loginUser==null){
                System.out.println("Bạn chưa đăng nhập!");
                System.out.println("Bạn có muốn đăng nhập không?Y/N");
                String confirmAction = Config.getString();
                if(confirmAction.equalsIgnoreCase("Y")) {
                    new UserView().formLogin();
                }else {
                    new NarBar();
                }
            }else {
                if (likedFilm){
                    likeList.remove(likedUser);
                    System.out.println("UnLike thành công");
                }else {
                    int likeId = 1;
                    if (likeList.size()!=0){
                        Like lastLike = likeList.get(likeList.size()-1);
                        likeId=lastLike.getLikeId()+1;
                    }
                    Like newLike = new Like(likeId,loginUser);
                    likeList.add(newLike);
                    System.out.println("Like thành công");
                }
                filmController.likeUnlikeFilm(film);
                System.out.println("Nhấn phím bất kì để thoát");
                Config.getString();
                new NarBar();
            }
        }
        else {
            System.out.println("Lựa chọn không hợp lệ!");
            watchAndLikeFilm(filmList,film);
        }
    }

    private void findFilm() {
        System.out.println("*-------------------------------------------------------------------------------*");
        System.out.println("|                          TÌM KIẾM                                             |");
        System.out.println("|-------------------------------------------------------------------------------|");
        System.out.println("| 1. Tìm kiếm theo tên.                                                         |");
        System.out.println("| 2. Tìm kiếm theo danh mục.                                                    |");
        System.out.println("| 9. Quay lại.                                                                  |");
        System.out.println("*-------------------------------------------------------------------------------*");
        System.out.print("Nhập vào lựa chọn của bạn: ");
        int choice = Config.getInteger();
        switch (choice) {
            case 1:
                findFilmByName();
                break;
            case 2:
                findFilmByCategory();
                break;
            case 9:
                findFilm();
                break;
            default:
                System.out.println("Không hợp lệ!!!");
                findFilm();
        }
    }

    private void findFilmByCategory() {
        System.out.println("Nhập vào tên danh mục muốn tìm kiếm: ");
        String findCategory = Config.getString();
        List<Film> filmList = filmController.findByCategory(findCategory);
        if (filmList.size() == 0) {
            System.out.println("Không tìm thấy kết quả!!!");
        } else {
            for (Film film : filmList) {
                film.displayData();
            }
        }
        findFilm();
    }

    private void findFilmByName() {
        while (true) {
            try {
                System.out.println("Nhập vào tên Film muốn tìm : ");
                String filmName = Config.getString();
                List<Film> filmList = filmController.findByName(filmName);

                if (filmList != null) {
                    for (int i = 0; i < filmList.size(); i++) {
                        filmList.get(i).displayData();
                    }
                } else {
                    System.out.println("Film này không tồn tại.");
                }
                System.out.println("Ấn phím bất kỳ để tiếp tục hoặc ấn Back để quay lại!!!");
                String backMenu = Config.getString();
                if (backMenu.equalsIgnoreCase("back")) {
                    findFilm();
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new InitData().initAllData();
        User loginUser = userController.getUserLogin();
        if (loginUser != null) {
            Set<Role> roleLogin = loginUser.getRoles();
            for (Role role : roleLogin) {
                if (role.getName().equals(RoleName.USER)) {
                    new NarBar();
                } else if (role.getName().equals(RoleName.ADMIN)) {
                    new AdminView();
                } else if (role.getName().equals(RoleName.PM)) {
                    new PmView();
                } else {
                    new NarBar();
                }
            }
        } else {
            new NarBar();
        }
    }
}
