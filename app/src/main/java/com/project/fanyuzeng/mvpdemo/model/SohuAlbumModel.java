package com.project.fanyuzeng.mvpdemo.model;

import android.text.TextUtils;
import android.util.Log;

import com.project.fanyuzeng.mvpdemo.Constants;
import com.project.fanyuzeng.mvpdemo.presenter.IBasePaginationPresenter;
import com.project.fanyuzeng.mvpdemo.response.BasePeginationParam;
import com.project.fanyuzeng.mvpdemo.utils.HttpUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by fanyuzeng on 2017/10/23.
 * Function:
 */
public class SohuAlbumModel<Param extends BasePeginationParam> implements IBaseModel<Param> {
    private static final String TAG = "SohuAlbumModel";
    private String url;
    private int method;
    private IBasePaginationPresenter mPaginationPresenter;

    public SohuAlbumModel(IBasePaginationPresenter paginationPresenter) {
        mPaginationPresenter = paginationPresenter;
    }

    @Override
    public void sendRequestToServer(Param param) {
        String validUrl = null;
        if (param != null && !TextUtils.isEmpty(url)) {
            validUrl = getValidUrl(url, param);
            Log.d(TAG, ">> sendRequestToServer >> " + "ValidUrl:" + validUrl);
        }

        if (!TextUtils.isEmpty(validUrl)) {
            HttpUtils.executeByGet(validUrl, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.d(TAG, ">> onFailure >> ");
                    e.printStackTrace();
                    mPaginationPresenter.okHttpError(Constants.URL_ERROR, e.getMessage(), url);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (!response.isSuccessful()) {
                        Log.d(TAG, ">> onResponse >> " + "Not successful");
                        mPaginationPresenter.okHttpError(Constants.SERVER_ERROR, response.message(), url);
                    }

                    String responseJson = response.body().string();
                    Log.d(TAG, ">> onResponse >> " + "responseJson:" + responseJson);
                    mPaginationPresenter.accessSuccess(responseJson);

                }
            });
        } else {
            Log.d(TAG, ">> sendRequestToServer >> " + "Valid Url is empty");
        }
    }

    private String getValidUrl(String url, Param param) {
        return String.format(url, param.getPageIndex(), param.getPageSize());
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
