package app.model;

import java.io.Serializable;
import java.util.Date;

public class Like implements Serializable {
    private int likeId;
    private User likeUser;
    private Date likedTime = new Date();

    public Like() {
    }

    public Like(int likeId, User likeUser) {
        this.likeId = likeId;
        this.likeUser = likeUser;
    }

    public int getLikeId() {
        return likeId;
    }

    public void setLikeId(int likeId) {
        this.likeId = likeId;
    }

    public User getLikeUser() {
        return likeUser;
    }

    public void setLikeUser(User likeUser) {
        this.likeUser = likeUser;
    }

    public Date getLikedTime() {
        return likedTime;
    }

    public void setLikedTime(Date likedTime) {
        this.likedTime = likedTime;
    }

    @Override
    public String toString() {
        return "Like{" +
                "likeId=" + likeId +
                ", likeUser=" + likeUser +
                ", likedTime=" + likedTime +
                '}';
    }
}
