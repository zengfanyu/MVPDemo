package com.project.fanyuzeng.mvpdemo.view;

import com.project.fanyuzeng.mvpdemo.response.VideoInfo;

import java.util.List;

/**
 * 展示搜狐电视剧频道具体信息的接口
 *
 * @author：ZengFanyu
 */
public interface ISohuSerials extends IBaseView {

    /**
     * 展示搜狐视频API电视剧主要信息的方法
     *
     * @param videoList 处理好的VideoInfo集合
     */
    void showAlbumMainInfo(List<VideoInfo> videoList);
}
