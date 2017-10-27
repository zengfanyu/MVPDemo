package com.project.fanyuzeng.mvpdemo.utils.okhttp.response;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.project.fanyuzeng.mvpdemo.utils.okhttp.exception.OkHttpException;
import com.project.fanyuzeng.mvpdemo.utils.okhttp.listener.DisposeDataHandler;
import com.project.fanyuzeng.mvpdemo.utils.okhttp.listener.DisposeDataListener;

import org.json.JSONException;
import org.json.JSONObject;

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
    private static final String ERROR_MSG = "emsg";
    private static final String EMPTY_MSG = "";
    /**
     * 有返回则对于http请求来说是成功的，但还有可能是业务逻辑上的错误
     */
    private static final String RESULT_CODE = "ecode";
    private static final int RESULT_CODE_VALUE = 0;

    private static final int NETWORK_ERROR = -1;
    private static final int JSON_ERROR = -2;
    private static final int OTHER_ERROR = -3;

    private DisposeDataListener mDisposeDataListener;
    private Class<?> mClass;
    private Handler mDeliveryHandler = new Handler(Looper.getMainLooper());

    private Gson mGson = new Gson();


    public CommonJsonCallback(DisposeDataHandler dataHandler) {
        mDisposeDataListener = dataHandler.mListener;
        mClass = dataHandler.mClass;
    }


    @Override
    public void onFailure(Call call, final IOException e) {
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                mDisposeDataListener.onFailure(e);
            }
        });
    }

    @Override
    public void onResponse(Call call, final Response response) throws IOException {
        if (!response.isSuccessful()) {
            mDeliveryHandler.post(new Runnable() {
                @Override
                public void run() {
                    mDisposeDataListener.onFailure(response.message());
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
        if (!TextUtils.isEmpty(resultJson)) {
            mDisposeDataListener.onFailure(new OkHttpException(NETWORK_ERROR, EMPTY_MSG));
            return;
        }

        try {
            JSONObject resultObj = new JSONObject(resultJson);
            // TODO: 2017/10/27 修改此处的条件与真实情况相符
            if (resultObj.has(RESULT_CODE)) {
                if (resultObj.optInt(RESULT_CODE) == RESULT_CODE_VALUE) {
                    if (mClass == null) {
                        mDisposeDataListener.onSuccess(resultObj);
                    } else {

                        Object mappedDataType = mGson.fromJson(resultJson, mClass);

                        if (mappedDataType == null) {
                            mDisposeDataListener.onFailure(new OkHttpException(NETWORK_ERROR, EMPTY_MSG));
                        } else {
                            mDisposeDataListener.onSuccess(mappedDataType);
                        }
                    }

                } else {
                    mDisposeDataListener.onFailure(new OkHttpException(JSON_ERROR, EMPTY_MSG));
                }
            }
        } catch (JSONException e) {
            mDisposeDataListener.onFailure(new OkHttpException(OTHER_ERROR, e.getMessage()));
        }
    }
}
