package com.project.fanyuzeng.mvpdemo.response;

import com.google.gson.annotations.SerializedName;

/**
 * @author：ZengFanyu
 * Function:
 */
public class VideoInfo {
    @SerializedName("main_actor")
    private String mMainActor;
    @SerializedName("total_video_count")
    private int mTotalVideoCount;
    @SerializedName("album_name")
    private String mAlbumName;
    @SerializedName("director")
    private String mDirector;
    @SerializedName("publish_time")
    private String mPublishTime;

    public String getMainActor() {
        return mMainActor;
    }

    public void setMainActor(String mainActor) {
        this.mMainActor = mainActor;
    }

    public String getTotalVideoCount() {
        return mTotalVideoCount +"";
    }

    public void setTotalVideoCount(int totalVideoCount) {
        this.mTotalVideoCount = totalVideoCount;
    }

    public String getAlbumName() {
        return mAlbumName;
    }

    public void setAlbumName(String albumName) {
        this.mAlbumName = albumName;
    }

    public String getDirector() {
        return mDirector;
    }

    public void setDirector(String director) {
        this.mDirector = director;
    }

    public String getPublishTime() {
        return mPublishTime;
    }

    public void setPublishTime(String publishTime) {
        this.mPublishTime = publishTime;
    }
}
