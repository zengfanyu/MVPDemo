package com.project.fanyuzeng.mvpdemo.presenter;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.project.fanyuzeng.mvpdemo.Constants;
import com.project.fanyuzeng.mvpdemo.response.Album;
import com.project.fanyuzeng.mvpdemo.response.BasePaginationParam;
import com.project.fanyuzeng.mvpdemo.view.ISohuSerials;

/**
 * @author：ZengFanyu
 * Function:
 */
public class AlbumPresenter extends BasePaginationPresenter<BasePaginationParam, Album> {
    private static final String TAG = "AlbumPresenter";
    private ISohuSerials mBaseListView;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private int mTotalCount=-1;

    public AlbumPresenter(ISohuSerials baseListView, Class<Album> CLazz) {
        super(baseListView, CLazz);
        this.mBaseListView = baseListView;
        getModel().setRequestMethod(Constants.HTTP_GET_METHOD);
        getModel().setRequestUrl(Constants.SOHU_SERIALS_URL);
    }

    @Override
    public void serverResponse(Album album) {

        Log.d(TAG,">> serverResponse >> " + album.toString());

        mBaseListView.showAlbumMainInfo(album.getData().getVideos());

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mBaseListView.showProgress(false);
            }
        });

        mTotalCount = album.getData().getCount();

    }

    @Override
    public boolean serverHaveMoreData() {
        //此处pageIndex是从1开始的， 实际适用需要注意pageIndex的起始值
        int pageSize = mParam.getPageSize();
        int pageIndex = mParam.getPageIndex();
        Log.d(TAG,">> serverHaveMoreData >> " + "pageSize："+pageSize+"pageIndex:"+pageIndex+"totalCount:"+mTotalCount);
        if (mTotalCount == -1){
            //第一次需要返回true 才能进到 serverResponse 方法中去初始化 mTotalCount 值
            return true;
        }
        return (pageIndex * pageSize) <= mTotalCount;
    }
}
