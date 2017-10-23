package com.project.fanyuzeng.mvpdemo.model;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.project.fanyuzeng.mvpdemo.Constants;
import com.project.fanyuzeng.mvpdemo.presenter.IBasePresenter;
import com.project.fanyuzeng.mvpdemo.utils.HttpUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by fanyuzeng on 2017/10/20.
 * Function:
 */
public class LatestNewsModel<Param> implements IBaseModel<Param> {
    private static final String TAG = "ReqLaNewModelFromBase";
    private String url;
    //默认为Get方式 ，根据这个参数来区别不同方式的请求
    private int method;
    private IBasePresenter mBasePresenter;

    public LatestNewsModel(IBasePresenter basePresenter) {
        mBasePresenter = basePresenter;
    }

    @Override
    public void sendRequestToServer(@Nullable Param param) {

        HttpUtils.executeByGet(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, ">> onFailure >> ");
                e.printStackTrace();
                mBasePresenter.okHttpError(Constants.URL_ERROR,e.getMessage(),url);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    Log.d(TAG, ">> onResponse >> " + "Not successful");
                    mBasePresenter.okHttpError(Constants.SERVER_ERROR,response.message(),url);

                }
                String mLatestNewsJson = response.body().string();
                if (!TextUtils.isEmpty(mLatestNewsJson)) {
                    mBasePresenter.accessSuccess(mLatestNewsJson);
                }
            }
        });
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
