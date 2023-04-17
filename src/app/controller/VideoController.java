package app.controller;

import app.model.Category;
import app.model.Film;
import app.model.Video;
import app.service.video.VideoServiceIPML;

import java.util.List;

public class VideoController {
    VideoServiceIPML videoServiceIPML = new VideoServiceIPML();
    public List<Video> getListVideo(){
        return videoServiceIPML.findAll();
    }
    public void createVideo(Video video){
        videoServiceIPML.save(video);
    }
    public void update(Video video){
        videoServiceIPML.save(video);
    }
    public Video findById(int id){
        return videoServiceIPML.findById(id);
    }

    public Video detailVideo(int id){
        return videoServiceIPML.findById(id);
    }

    public void deleteById(int deleteId) {
        videoServiceIPML.delete(deleteId);
    }

    public List<Video> findVideoByCategory(Category category) {
        return  videoServiceIPML.findByCategory(category);
    }
}
