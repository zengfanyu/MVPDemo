package com.project.fanyuzeng.mvpdemo.utils;

import com.project.fanyuzeng.mvpdemo.AppManager;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;

/**
 * Created by fanyuzeng on 2017/10/20.
 * Function:
 */
public class HttpUtils {
    public static final String GET_LATEST_NEWS = "get_latest_news";
    private static Call sCall;

    private static Request buildGetRequest(String url) {
        return new Request.Builder()
                .tag(GET_LATEST_NEWS)
                .url(url)
                .build();
    }

    public static void executeByGet(String url, Callback callback) {
        Request request = buildGetRequest(url);
        executeByGet(request, callback);

    }

    private static void executeByGet(Request request, Callback callback) {
        sCall = AppManager.getOkHttpClient().newCall(request);
        sCall.enqueue(callback);
    }

    public static void cancelCall() {
        sCall.cancel();
    }

}
