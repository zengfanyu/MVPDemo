package com.project.fanyuzeng.mvpdemo.model;

import android.text.TextUtils;
import android.util.Log;

import com.project.fanyuzeng.mvpdemo.Constants;
import com.project.fanyuzeng.mvpdemo.presenter.ILatestNewsPresenter;
import com.project.fanyuzeng.mvpdemo.utils.HttpUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by fanyuzeng on 2017/10/20.
 * Function:
 */
public class RequestLatestNewsModel implements IRequestLatestModel {

    private static final String TAG = "RequestLatestNewsModel";

    private ILatestNewsPresenter mLatestNewsPresenter;

    public RequestLatestNewsModel(ILatestNewsPresenter latestNewsPresenter) {
        mLatestNewsPresenter = latestNewsPresenter;
    }

    @Override
    public void requestLatestNews() {
        HttpUtils.executeByGet(Constants.LATEST_NEWS, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, ">> onFailure >> ");
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    Log.d(TAG, ">> onResponse >> " + "Not successful");
                }

                String latestNewsJson = response.body().string();
                if (!TextUtils.isEmpty(latestNewsJson)){
                    mLatestNewsPresenter.handleData(latestNewsJson);
                }
            }
        });
    }
}
