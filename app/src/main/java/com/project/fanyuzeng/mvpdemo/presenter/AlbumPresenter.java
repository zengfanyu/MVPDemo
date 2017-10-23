package com.project.fanyuzeng.mvpdemo.presenter;

import android.os.Handler;
import android.os.Looper;

import com.project.fanyuzeng.mvpdemo.Constants;
import com.project.fanyuzeng.mvpdemo.response.Album;
import com.project.fanyuzeng.mvpdemo.response.BasePeginationParam;
import com.project.fanyuzeng.mvpdemo.view.ISOHUSerials;

/**
 * Created by fanyuzeng on 2017/10/23.
 * Function:
 */
public class AlbumPresenter extends BasePaginationPresenter<BasePeginationParam, Album> {
    private ISOHUSerials mBaseListView;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    public AlbumPresenter(ISOHUSerials baseListView, Class<Album> CLazz) {
        super(baseListView, CLazz);
        this.mBaseListView = baseListView;
        getModel().setRequestMethod(Constants.HTTP_GET_METHOD);
        getModel().setRequestUrl(Constants.SOHU_SERIALS_URL);
    }

    @Override
    public void serverResponse(Album album) {

        mBaseListView.showMainInfo(album.getData().getVideos());

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mBaseListView.showProgress(false);
            }
        });

    }
}
