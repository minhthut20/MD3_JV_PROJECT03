package app.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Video implements Serializable {
    private int videoId;
    private String videoName;
    private String videoDate;
    private Category category;
    private List<Like> likeList = new ArrayList<>();
    private List<View> viewList = new ArrayList<>();

    public Video() {
    }

    public Video(int videoId, String videoName, String videoDate, Category category) {
        this.videoId = videoId;
        this.videoName = videoName;
        this.videoDate = videoDate;
        this.category = category;
    }

    public Video(int videoId, String videoName, String videoDate, Category category, List<Like> likeList, List<View> viewList) {
        this.videoId = videoId;
        this.videoName = videoName;
        this.videoDate = videoDate;
        this.category = category;
        this.likeList = likeList;
        this.viewList = viewList;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoDate() {
        return videoDate;
    }

    public void setVideoDate(String videoDate) {
        this.videoDate = videoDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Like> getLikeList() {
        return likeList;
    }

    public void setLikeList(List<Like> likeList) {
        this.likeList = likeList;
    }

    public List<View> getViewList() {
        return viewList;
    }

    public void setViewList(List<View> viewList) {
        this.viewList = viewList;
    }

    public void displayData() {
        System.out.println("*-------------------------------------------------------------------------------*");
        System.out.println("|                          THÔNG TIN VIEO                                       |");
        System.out.println("*-------------------------------------------------------------------------------*");
        System.out.println("|                                                                               |");
        System.out.printf("|        ID: %-20s           Tên : %-30s|\n", String.valueOf(videoId), videoName);
        System.out.printf("|        Năm phát hành: %-9s           Danh mục: %-26s|\n", videoDate, category.getCategoryName());
        System.out.println("|                                                                               |");
        System.out.println("*-------------------------------------------------------------------------------*");
    }

    @Override
    public String toString() {
        return "Video{" +
                "videoId=" + videoId +
                ", videoName='" + videoName + '\'' +
                ", videoDate='" + videoDate + '\'' +
                ", category=" + category +
                ", likeList=" + likeList +
                ", viewList=" + viewList +
                '}';
    }
}
