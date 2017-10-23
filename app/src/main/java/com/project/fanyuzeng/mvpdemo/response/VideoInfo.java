package com.project.fanyuzeng.mvpdemo.response;

/**
 * Created by fanyuzeng on 2017/10/23.
 * Function:
 */
public class VideoInfo {
    private String main_actor;
    private int total_video_count;
    private String album_name;
    private String director;
    private String publish_time;

    public String getMain_actor() {
        return main_actor;
    }

    public void setMain_actor(String main_actor) {
        this.main_actor = main_actor;
    }

    public String getTotal_video_count() {
        return total_video_count+"";
    }

    public void setTotal_video_count(int total_video_count) {
        this.total_video_count = total_video_count;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public void setAlbum_name(String album_name) {
        this.album_name = album_name;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(String publish_time) {
        this.publish_time = publish_time;
    }
}
