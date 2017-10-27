package com.project.fanyuzeng.mvpdemo.utils.okhttp.response;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.project.fanyuzeng.mvpdemo.utils.okhttp.exception.OkHttpException;
import com.project.fanyuzeng.mvpdemo.utils.okhttp.listener.DisposeDataHandler;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author:fanyuzeng
 * @date: 2017/10/27 14:41
 * @desc:
 */
public class CommonJsonCallback implements Callback {
    private static final String TAG = "CommonJsonCallback";
    private static final String MSG_RESULT_EMPTY = "request could not be ececuted";
    private static final String MSG_JSON_EMPTY = "json is empty or null";
    private static final String MSG_RETURN_CODE = "http return code is not [200,300)";


    private static final int NETWORK_ERROR = -1;
    private static final int JSON_ERROR = -2;

    private Handler mDeliveryHandler = new Handler(Looper.getMainLooper());

    private Gson mGson = new Gson();

    private DisposeDataHandler mDisposeDataHandler;


    public CommonJsonCallback(DisposeDataHandler dataHandler) {
        mDisposeDataHandler = dataHandler;
    }


    @Override
    public void onFailure(@NonNull Call call, @NonNull final IOException e) {
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                mDisposeDataHandler.onFailure(new OkHttpException(NETWORK_ERROR, MSG_RESULT_EMPTY + e.getMessage()));
            }
        });
    }

    @Override
    public void onResponse(@NonNull Call call, @NonNull final Response response) throws IOException {
        if (!response.isSuccessful()) {
            mDeliveryHandler.post(new Runnable() {
                @Override
                public void run() {
                    mDisposeDataHandler.onFailure(new OkHttpException(NETWORK_ERROR, MSG_RETURN_CODE + response.message()));
                }
            });
        }
        final String resultJson = response.body().string();
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                handleResponse(resultJson);
            }
        });
    }

    private void handleResponse(String resultJson) {
        if (TextUtils.isEmpty(resultJson)) {
            mDisposeDataHandler.onFailure(new OkHttpException(NETWORK_ERROR, MSG_JSON_EMPTY));
            return;
        }
        if (mDisposeDataHandler.getClassType() == null) {
            mDisposeDataHandler.onSuccess(resultJson);
        } else {
            Object mappedDataType = mGson.fromJson(resultJson, mDisposeDataHandler.getClassType());
            if (mappedDataType == null) {
                mDisposeDataHandler.onFailure(new OkHttpException(JSON_ERROR, MSG_JSON_EMPTY));
            } else {
                mDisposeDataHandler.onSuccess(mappedDataType);
            }
        }


    }
}
