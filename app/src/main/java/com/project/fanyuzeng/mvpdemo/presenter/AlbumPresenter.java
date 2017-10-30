package com.project.fanyuzeng.mvpdemo.presenter;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.project.fanyuzeng.mvpdemo.BaseMvpActivity;
import com.project.fanyuzeng.mvpdemo.Constants;
import com.project.fanyuzeng.mvpdemo.response.Album;
import com.project.fanyuzeng.mvpdemo.response.BasePaginationParam;

import java.util.HashMap;

/**
 * @author：ZengFanyu
 * Function:
 */
public class AlbumPresenter extends BasePaginationPresenter<BasePaginationParam, Album> {
    private static final String TAG = "AlbumPresenter";
    private BaseMvpActivity mBaseListView;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private int mTotalCount=-1;

    public AlbumPresenter(BaseMvpActivity baseListView, Class<Album> aClass) {
        super(baseListView, aClass);
        this.mBaseListView = baseListView;
        getModel().setRequestMethod(Constants.HTTP_GET_METHOD);
        getModel().setRequestUrl(Constants.SOHU_SERIALS_URL_BASE);
    }

    @Override
    public void serverResponse(Album album) {

        Log.d(TAG,">> serverResponse >> " + album.toString());

        mBaseListView.showDataFromPresenter(album.getData().getVideos());

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
        Log.d(TAG, ">> serverHaveMoreData >> " + "pageSize：" + pageSize + "pageIndex:" + pageIndex + "totalCount:" + mTotalCount);
        //第一次需要返回true 才能进到 serverResponse 方法中去初始化 mTotalCount 值
        return mTotalCount == -1 || (pageIndex * pageSize) <= mTotalCount;
    }

    @Override
    public HashMap<String, String> getHttpRequestParams() {
        HashMap<String, String> paramsMap = new HashMap<>();
        paramsMap.put("cid", "2");
        paramsMap.put("o", "1");
        paramsMap.put("plat", "6");
        paramsMap.put("poid", "1");
        paramsMap.put("api_key", "9854b2afa779e1a6bff1962447a09dbd");
        paramsMap.put("sver", "6.2.0");
        paramsMap.put("sysver", "4.4.2");
        paramsMap.put("partner", "47");
        paramsMap.put("page", String.valueOf(mParam.getPageIndex()));
        paramsMap.put("page_size", String.valueOf(mParam.getPageSize()));
        return paramsMap;
    }

}
