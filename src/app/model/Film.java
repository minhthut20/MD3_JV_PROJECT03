package app.model;

import app.config.Config;
import app.service.film.FilmServiceIMPL;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Film implements Serializable {
    private int filmId;
    private String filmName;
    private String description;
    private String releasedYear;
    private String nation;
    private boolean status;

    private Category category;
    private List<Video> episodeList = new ArrayList<>();

    private List<Like> likeFilmList = new ArrayList<>();
    private List<View> viewFilmList = new ArrayList<>();

    public Film(int filmId, String filmName, String description, Category category, String releasedYear, String nation, boolean status, List<Video> episodeList) {
        this.filmId = filmId;
        this.filmName = filmName;
        this.description = description;
        this.category = category;
        this.releasedYear = releasedYear;
        this.nation = nation;
        this.status = status;
        this.episodeList = episodeList;
    }

    public Film(int filmId, String filmName, String description, Category category, String releasedYear, String nation, boolean status, List<Video> episodeList, List<Like> likeFilmList, List<View> viewFilmList) {
        this.filmId = filmId;
        this.filmName = filmName;
        this.description = description;
        this.releasedYear = releasedYear;
        this.nation = nation;
        this.status = status;
        this.category = category;
        this.episodeList = episodeList;
        this.likeFilmList = likeFilmList;
        this.viewFilmList = viewFilmList;
    }

    public List<Like> getLikeFilmList() {
        return likeFilmList;
    }

    public void setLikeFilmList(List<Like> likeFilmList) {
        this.likeFilmList = likeFilmList;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getReleasedYear() {
        return releasedYear;
    }

    public void setReleasedYear(String releasedYear) {
        this.releasedYear = releasedYear;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Video> getEpisodeList() {
        return episodeList;
    }

    public void setEpisodeList(List<Video> episodeList) {
        this.episodeList = episodeList;
    }

    public List<View> getViewFilmList() {
        return viewFilmList;
    }

    public void setViewFilmList(List<View> viewFilmList) {
        this.viewFilmList = viewFilmList;
    }

    public void inputData() {
        System.out.println("-------------NHẬP THÔNG TIN FILM--------------------");
        FilmServiceIMPL filmServiceIMPL = new FilmServiceIMPL();
        int maxId = 0;
        List<Film> filmList = filmServiceIMPL.findAll();
        if (filmList.size() != 0) {
            maxId = filmList.get(0).getFilmId();
            for (int i = 0; i < filmList.size(); i++) {
                int currentId = filmList.get(i).getFilmId();
                if (currentId > maxId) {
                    maxId = currentId;
                }
            }
        }
        setFilmId(++maxId);
        System.out.print("NHập tên Film : ");
        setFilmName(Config.getString());
        System.out.print("Mô tả Film : ");
        setDescription(Config.getString());

        System.out.print("Năm phát hành : ");
        setReleasedYear(Config.getString());
        System.out.print("Quốc gia : ");
        setNation(Config.getString());
        System.out.print("Tình trạng : ");
        setStatus(Config.getBoolean());
        System.out.println("------------------------------------------------------");
    }

    public void displayData() {
        System.out.println("*-------------------------------------------------------------------------------*");
        System.out.printf("|                                %-47s|\n", filmName);
        System.out.println("*-------------------------------------------------------------------------------*");
        System.out.printf("|  Phim ID: %-18s Lượt like: %-38s|\n", String.valueOf(filmId),likeFilmList.size());
        System.out.println("|                                                                               |");
        System.out.printf("|  Trạng thái: %-15s Năm phát hành: %-9s Quốc gia: %-14s|\n", (status ? "Đang chiếu" : "Sắp chiếu"), releasedYear, nation);
        System.out.println("|                                                                               |");
        System.out.printf("|  Thể loại: %-17s Lượt xem: %-14s Số tập: %-16s|\n", category.getCategoryName(), viewFilmList.size(), episodeList.size());
        System.out.println("|                                                                               |");
        System.out.println("*-------------------------------------------------------------------------------*");
    }

    @Override
    public String toString() {
        return "Film{" +
                "filmId=" + filmId +
                ", filmName='" + filmName + '\'' +
                ", description='" + description + '\'' +
                ", releasedYear='" + releasedYear + '\'' +
                ", nation='" + nation + '\'' +
                ", status=" + status +
                ", category=" + category +
                ", episodeList=" + episodeList +
                ", likeFilmList=" + likeFilmList +
                ", viewVideoList=" + viewFilmList +
                '}';
    }
}

