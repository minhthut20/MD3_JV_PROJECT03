package app.model;

import java.io.Serializable;
import java.util.Date;

public class View implements Serializable {
    private int viewId;
    private User viewUser;
    private Date viewedTime = new Date();

    public View() {
    }

    public View(int viewId, User viewUser) {
        this.viewId = viewId;
        this.viewUser = viewUser;
    }

    public int getViewId() {
        return viewId;
    }

    public void setViewId(int viewId) {
        this.viewId = viewId;
    }

    public User getViewUser() {
        return viewUser;
    }

    public void setViewUser(User viewUser) {
        this.viewUser = viewUser;
    }

    public Date getViewedTime() {
        return viewedTime;
    }

    public void setViewedTime(Date viewedTime) {
        this.viewedTime = viewedTime;
    }

    @Override
    public String toString() {
        return "View{" +
                "viewId=" + viewId +
                ", viewUser=" + viewUser +
                ", viewedTime=" + viewedTime +
                '}';
    }
}
