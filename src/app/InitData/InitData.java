package app.InitData;

import app.config.Config;
import app.model.*;
import database.DataBase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InitData {
    private User user1, user2, user3, user4, user5, user6, user7, user8, user9, user10;
    private List<Video> epFilm1, epFilm2, epFilm3, epFilm4, epFilm5, epFilm6, epFilm7, epFilm8;
    private List<Like> likeListFilm1, likeListFilm2, likeListFilm3, likeListFilm4, likeListFilm5, likeListFilm6, likeListFilm7, likeListFilm8;
    private List<View> viewListFilm1, viewListFilm2, viewListFilm3, viewListFilm4, viewListFilm5, viewListFilm6, viewListFilm7, viewListFilm8;
    private Category KhoaHoc, KiemHiep, KinhDi, GiaDinh, HocDuong, Anime, TVShow, ChieuRap ;
    private Set<Role> adminRole, pmRole, userRole;
    private Video epFlim21, epFilm22, epFilm41,epFilm42,epFilm43, epFilm51,epFilm52,epFilm71,epFilm72,epFilm73,epFilm74;

    public void initAllData() {

        intRoleDB();
        initUserData();
        initCategory();
        if (new Config<Category>().readFromFile(DataBase.PATH_CATEGORY).size() != 0) {
            initVideo();
            initFilm();
        }
    }

    private void intRoleDB() {
        adminRole = new HashSet<>();
        adminRole.add(new Role(1, RoleName.ADMIN));
        pmRole = new HashSet<>();
        pmRole.add(new Role(2, RoleName.PM));
        userRole = new HashSet<>();
        userRole.add(new Role(3, RoleName.USER));
    }

    private void initUserData() {
        List<User> userList = new Config<User>().readFromFile(DataBase.PATH_USER);
        if (userList.size() == 0) {
            User admin, pm;
            admin = new User(1, "Admin", "admin", "admin@gmail.com", "Admin@123", adminRole);
            pm = new User(2, "Chuan", "DoChuan", "chuan@gmail.com", "Dochuan@123", pmRole);
            user1 = new User(3, "Vuong", "MinhVuong", "vuong@gmail.com", "Minhvuong@123", userRole);
            user2 = new User(4, "Thu", "MinhThu", "thu@gmail.com", "Minhthu@123", userRole);
            user3 = new User(5, "Yen", "HaiYen", "yen@gmail.com", "Haiyen@123", userRole);
            user4 = new User(6, "Van", "ThanhVan", "van@gmail.com", "Thanhvan@123", userRole);
            user5 = new User(7, "Son", "ManhSon", "son@gmail.com", "Manhson@123", userRole);
            user6 = new User(8, "Huy", "HuuHuy", "huy@gmail.com", "Huuhuy@123", userRole);
            user7 = new User(9, "Dat", "DucDat", "dat@gmail.com", "Ducdat@123", userRole);
            user8 = new User(10, "Hong", "DinhHong", "hong@gmail.com", "Dinhong@123", userRole);
            user9 = new User(11, "Ha", "ThuHa", "ha@gmail.com", "Thuha@123", userRole);
            user10 = new User(12, "Minh", "DucMinh", "minh@gmail.com", "Ducminh@123", userRole);

            userList.add(admin);
            userList.add(pm);
            userList.add(user1);
            userList.add(user2);
            userList.add(user3);
            userList.add(user4);
            userList.add(user5);
            userList.add(user6);
            userList.add(user7);
            userList.add(user8);
            userList.add(user9);
            userList.add(user10);

            new Config<User>().writeFile(DataBase.PATH_USER, userList);
        }
    }

    private void initCategory() {
        List<Category> categoryList = new Config<Category>().readFromFile(DataBase.PATH_CATEGORY);
        if (categoryList.size() == 0) {
            KhoaHoc = new Category(1, "Khoa Học");
            KiemHiep = new Category(2, "Kiếm Hiệp");
            KinhDi = new Category(3, "Kinh Dị");
            GiaDinh = new Category(4, "Gia Đình");
            HocDuong = new Category(5, "Học Đường");
            Anime = new Category(6, "Anime");
            TVShow = new Category(7, "TVShow");
            ChieuRap = new Category(8, "Phim Chiếu Rạp");

            categoryList.add(KhoaHoc);
            categoryList.add(KiemHiep);
            categoryList.add(KinhDi);
            categoryList.add(GiaDinh);
            categoryList.add(HocDuong);
            categoryList.add(Anime);
            categoryList.add(TVShow);
            categoryList.add(ChieuRap);

            new Config<Category>().writeFile(DataBase.PATH_CATEGORY, categoryList);
        }
    }

    private void initVideo() {
        List<Video> videoList = new Config<Video>().readFromFile(DataBase.PATH_VIDEO);
        if (videoList.size() == 0) {
            Video video1, video2, video3, video4, video5, video6, video7, video8, video9, video10, video11, video12, video13, video14, video15, video16;
            video1 = new Video(1, "Avatar", "2022", KhoaHoc);
            video2 = new Video(2, "Mandalore", "2023", KhoaHoc);
            video3 = new Video(3, "Thần điêu đại hiệp", "2007", KiemHiep);
            video4 = new Video(4, "Thương Lan Quyết", "2022", KiemHiep);
            video5 = new Video(5, "Annable", "2019", KinhDi);
            video6 = new Video(6, "Chuyến tàu sinh tử", "2020", KinhDi);
            video7 = new Video(7, "Gia đình là số 1", "2023", GiaDinh);
            video8 = new Video(8, "Cây táo nở hoa", "2010", GiaDinh);
            video9 = new Video(9, "Vườn Sao Băng", "2012", HocDuong);
            video10 = new Video(10, "School 2015", "2015", HocDuong);
            video11 = new Video(11, "Your Name", "2021", Anime);
            video12 = new Video(12, "Inuyasha", "2015", Anime);
            video13 = new Video(13, "Running Man", "2012", TVShow);
            video14 = new Video(14, "Sasuke", "2015", TVShow);
            video15 = new Video(15, "Avenger", "2019", ChieuRap);
            video16 = new Video(16, "Tatanic", "1999", ChieuRap);

            epFlim21 = new Video(17,"Thương Lan Quyết_1","2022",KiemHiep);
            epFilm22 = new Video(18,"Thương Lan Quyết_2","2022",KiemHiep);
            epFilm41 = new Video(19,"Cô dâu 8 tuổi_1","2016",GiaDinh);
            epFilm42 = new Video(20,"Cô dâu 8 tuổi_2","2016",GiaDinh);
            epFilm43 = new Video(21,"Cô dâu 8 tuổi_3","2016",GiaDinh);
            epFilm51 = new Video(22,"School 2015_1","2015",HocDuong);
            epFilm52 = new Video(23,"School 2015_2","2015",HocDuong);
            epFilm71 = new Video(24,"Running Man_1","2015",TVShow);
            epFilm72 = new Video(25,"Running Man_2","2015",TVShow);
            epFilm73 = new Video(26,"Running Man_3","2015",TVShow);
            epFilm74 = new Video(27,"Running Man_4","2015",TVShow);


            videoList.add(video1);
            videoList.add(video2);
            videoList.add(video3);
            videoList.add(video4);
            videoList.add(video5);
            videoList.add(video6);
            videoList.add(video7);
            videoList.add(video8);
            videoList.add(video9);
            videoList.add(video10);
            videoList.add(video11);
            videoList.add(video12);
            videoList.add(video13);
            videoList.add(video14);
            videoList.add(video15);
            videoList.add(video16);

            videoList.add(epFlim21);
            videoList.add(epFilm22);
            videoList.add(epFilm41);
            videoList.add(epFilm42);
            videoList.add(epFilm43);
            videoList.add(epFilm51);
            videoList.add(epFilm52);
            videoList.add(epFilm71);
            videoList.add(epFilm72);
            videoList.add(epFilm73);
            videoList.add(epFilm74);

            new Config<Video>().writeFile(DataBase.PATH_VIDEO, videoList);
        }
    }

    private void initFilm() {
        List<Film> filmList = new Config<Film>().readFromFile(DataBase.PATH_FILM);
        if (filmList.size() == 0) {
            initEpFilm();
            initLikeFilm();
            initViewFilm();
            Film film1, film2, film3, film4, film5, film6, film7, film8;
            film1 = new Film(1, "Thị Trấn Smallvile", "Phim về siêu nhân", KhoaHoc, "2016", "England", true, epFilm1, likeListFilm1, viewListFilm1);
            film2 = new Film(2, "Thương Lan Quyết", "Phim kiếm hiệp", KiemHiep, "2022", "China", true, epFilm2, likeListFilm2, viewListFilm2);
            film3 = new Film(3, "Annable", "Phim về búp bê ma", KinhDi, "2019", "America", true, epFilm3, likeListFilm3, viewListFilm3);
            film4 = new Film(4, "Cô dâu 8 tuổi", "Phim về gia dinh", GiaDinh, "2016", "Indo", true, epFilm4, likeListFilm4, viewListFilm4);
            film5 = new Film(5, "School 2015", "School", HocDuong, "2015", "Korea", true, epFilm5, likeListFilm5, viewListFilm5);
            film6 = new Film(6, "Inuyasha", "Inu", Anime, "2015", "Japan", true, epFilm6, likeListFilm6, viewListFilm6);
            film7 = new Film(7, "Running Man", "Chương trình thực tế", TVShow, "2012", "Korea", true, epFilm7, likeListFilm7, viewListFilm7);
            film8 = new Film(8, "Sát Thủ Nhân Taọ", "Tạo ra con người", ChieuRap, "2021", "Korea", true, epFilm8, likeListFilm8, viewListFilm8);

            filmList.add(film1);
            filmList.add(film2);
            filmList.add(film3);
            filmList.add(film4);
            filmList.add(film5);
            filmList.add(film6);
            filmList.add(film7);
            filmList.add(film8);

            new Config<Film>().writeFile(DataBase.PATH_FILM, filmList);
        }
    }

    private void initEpFilm() {

        epFilm1 = new ArrayList<>();
        epFilm2 = new ArrayList<>();
        epFilm3 = new ArrayList<>();
        epFilm4 = new ArrayList<>();
        epFilm5 = new ArrayList<>();
        epFilm6 = new ArrayList<>();
        epFilm7 = new ArrayList<>();
        epFilm8 = new ArrayList<>();

        epFilm2.add(epFlim21);
        epFilm2.add(epFilm22);
        epFilm4.add(epFilm41);
        epFilm4.add(epFilm42);
        epFilm4.add(epFilm43);
        epFilm5.add(epFilm51);
        epFilm5.add(epFilm52);
        epFilm7.add(epFilm71);
        epFilm7.add(epFilm72);
        epFilm7.add(epFilm73);
        epFilm7.add(epFilm74);

    }

    private void initLikeFilm() {
        Like like11, like12, like13, like14, like21, like22, like23, like24, like25, like26,
                like71,like72,like73,like74,like75,like76;

        likeListFilm1 = new ArrayList<>();
        likeListFilm2 = new ArrayList<>();
        likeListFilm3 = new ArrayList<>();
        likeListFilm4 = new ArrayList<>();
        likeListFilm5 = new ArrayList<>();
        likeListFilm6 = new ArrayList<>();
        likeListFilm7 = new ArrayList<>();
        likeListFilm8 = new ArrayList<>();

        like11 = new Like(1, user1);
        like12 = new Like(2, user2);
        like13 = new Like(3, user3);
        like14 = new Like(4, user4);

        like21 = new Like(5, user5);
        like22 = new Like(6, user6);
        like23 = new Like(7, user7);
        like24 = new Like(8, user8);
        like25 = new Like(9, user9);
        like26 = new Like(10, user10);

        like71 = new Like(5, user5);
        like72 = new Like(6, user6);
        like73 = new Like(7, user7);
        like74 = new Like(8, user8);
        like75 = new Like(9, user9);
        like76 = new Like(10, user10);

        likeListFilm1.add(like11);
        likeListFilm1.add(like12);
        likeListFilm1.add(like13);
        likeListFilm1.add(like14);

        likeListFilm2.add(like21);
        likeListFilm2.add(like22);
        likeListFilm2.add(like23);
        likeListFilm2.add(like24);
        likeListFilm2.add(like25);
        likeListFilm2.add(like26);

        likeListFilm7.add(like71);
        likeListFilm7.add(like72);
        likeListFilm7.add(like73);
        likeListFilm7.add(like74);
        likeListFilm7.add(like75);
        likeListFilm7.add(like76);
    }

    private void initViewFilm() {
        View view11, view12, view13, view14, view21, view22, view23, view24, view25, view26;

        viewListFilm1 = new ArrayList<>();
        viewListFilm2 = new ArrayList<>();
        viewListFilm3 = new ArrayList<>();
        viewListFilm4 = new ArrayList<>();
        viewListFilm5 = new ArrayList<>();
        viewListFilm6 = new ArrayList<>();
        viewListFilm7 = new ArrayList<>();
        viewListFilm8 = new ArrayList<>();

        view11 = new View(1, user1);
        view12 = new View(2, user2);
        view13 = new View(3, user3);
        view14 = new View(4, user4);

        view21 = new View(5, user5);
        view22 = new View(6, user6);
        view23 = new View(7, user7);
        view24 = new View(8, user8);
        view25 = new View(9, user9);
        view26 = new View(10, user10);

        viewListFilm1.add(view11);
        viewListFilm1.add(view12);
        viewListFilm1.add(view13);
        viewListFilm1.add(view14);

        viewListFilm2.add(view21);
        viewListFilm2.add(view22);
        viewListFilm2.add(view23);
        viewListFilm2.add(view24);
        viewListFilm2.add(view25);
        viewListFilm2.add(view26);
    }
}
