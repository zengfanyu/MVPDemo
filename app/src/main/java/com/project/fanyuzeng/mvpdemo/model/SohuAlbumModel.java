package com.project.fanyuzeng.mvpdemo.model;

import android.support.annotation.Nullable;
import android.util.Log;

import com.project.fanyuzeng.mvpdemo.presenter.IBasePaginationPresenter;
import com.project.fanyuzeng.mvpdemo.response.BasePaginationParam;
import com.project.fanyuzeng.mvpdemo.utils.HttpUtils;
import com.project.fanyuzeng.mvpdemo.utils.okhttp.OkHttpManager;
import com.project.fanyuzeng.mvpdemo.utils.okhttp.exception.OkHttpException;
import com.project.fanyuzeng.mvpdemo.utils.okhttp.listener.DisposeDataHandler;
import com.project.fanyuzeng.mvpdemo.utils.okhttp.listener.DisposeDataListener;

/**
 * @author：ZengFanyu
 */
public class SohuAlbumModel<Param extends BasePaginationParam> implements IBaseModel<Param> {
    private static final String TAG = "SohuAlbumModel";
    private String url;
    private int method;
    private IBasePaginationPresenter mPaginationPresenter;

    public SohuAlbumModel(IBasePaginationPresenter paginationPresenter) {
        mPaginationPresenter = paginationPresenter;
    }

    @Override
    public void sendRequestToServer(@Nullable Param param) {
        OkHttpManager.getInstance().requestServerData(method, url, mPaginationPresenter.getParams(), new DisposeDataHandler(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                String responseJson = (String) responseObj;
                Log.d(TAG, ">> onSuccess >> " + responseJson);
                mPaginationPresenter.accessSuccess(responseJson);
            }
            @Override
            public void onFailure(OkHttpException exception) {
                Log.d(TAG, ">> onFailure >> " + exception.getErrorCode());
                mPaginationPresenter.okHttpError(exception.getErrorCode(), exception.getErrorMsg(), url);
            }
        },null));
    }

    @Override
    public void setRequestUrl(String url) {
        this.url = url;
    }

    @Override
    public void setRequestMethod(int method) {
        this.method = method;
    }

    @Override
    public void cancelRequest() {
        HttpUtils.cancelCall();
    }
}
