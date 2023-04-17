package app.service.video;

import app.config.Config;
import app.model.Category;
import app.model.Film;
import app.model.Video;
import database.DataBase;

import java.util.ArrayList;
import java.util.List;

public class VideoServiceIPML implements IVideoService{
    List<Video> videoList = new Config<Video>().readFromFile(DataBase.PATH_VIDEO);

    @Override
    public List<Video> findAll() {
        return videoList;
    }

    @Override
    public void save(Video video) {
        Video video1 = findById(video.getVideoId());
        if (video1 ==null){
            videoList.add(video);
        }else {
            int index = videoList.indexOf(video1);
            videoList.set(index,video);
        }
        new Config<Video>().writeFile(DataBase.PATH_VIDEO,videoList );
    }

    @Override
    public Video findById(int id) {
        for (int i = 0; i < videoList.size(); i++) {
            Video video = videoList.get(i);
            if (video.getVideoId()==id){
                return video;
            }
        }
        return null;
    }

    @Override
    public void delete(int id) {
        videoList.remove(findById(id));
        new Config<Video>().writeFile(DataBase.PATH_VIDEO,videoList);
    }

    public List<Video> findByCategory(Category category) {
        List<Video> result = new ArrayList<>();
        for (int i = 0; i < videoList.size(); i++) {
            if (videoList.get(i).getCategory().getCategoryId()==category.getCategoryId()){
                result.add(videoList.get(i));
            }
        }
        return result;
    }
}
