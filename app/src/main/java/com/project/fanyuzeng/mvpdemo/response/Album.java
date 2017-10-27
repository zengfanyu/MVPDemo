package com.project.fanyuzeng.mvpdemo.response;

import java.util.List;

/**
 * Created by fanyuzeng on 2017/10/23.
 * Function:
 */
public class Album {

    private String statusText;
    private DataBean data;

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {


        private int count;
        private List<VideoInfo> videos;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<VideoInfo> getVideos() {
            return videos;
        }

        public void setVideos(List<VideoInfo> videos) {
            this.videos = videos;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "count=" + count +
                    ", videos=" + videos +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Album{" +
                "statusText='" + statusText + '\'' +
                ", data=" + data +
                '}';
    }
}
