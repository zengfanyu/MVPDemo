package com.project.fanyuzeng.mvpdemo.model;

import android.util.Log;

import com.project.fanyuzeng.mvpdemo.presenter.IBasePresenter;
import com.project.fanyuzeng.mvpdemo.utils.HttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by fanyuzeng on 2017/10/20.
 * Function:
 */
public class BaseModel implements IBaseModel {
    private static final String TAG = "ReqLaNewModelFromBase";
    private String url;
    private int method; //默认为Get方式
    private IBasePresenter mBasePresenter;

    public BaseModel(IBasePresenter basePresenter) {
        mBasePresenter = basePresenter;
    }

    @Override
    public void sendRequestToServer() {
        HttpUtils.executeByGet(url, new Callback() {


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
                String mLatestNewsJson = null;
                JSONObject JOResponse = null;

                try {
                    mLatestNewsJson = response.body().string();
                    JOResponse = new JSONObject(mLatestNewsJson);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (JOResponse != null) {
                    // TODO: 2017/10/20 此处传递过去的是stories的数组
                    mBasePresenter.requestSuccess(JOResponse);
                }
            }
        });
    }

    @Override
    public void setRequestUrl(String url) {
        this.url = url;
    }

    @Override
    public void setMethod(int method) {
        this.method = method;
    }

    @Override
    public void cancelRequest() {
        HttpUtils.cancelLatestNewsCall();
    }
}
