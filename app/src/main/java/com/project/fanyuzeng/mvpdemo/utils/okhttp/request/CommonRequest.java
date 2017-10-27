package com.project.fanyuzeng.mvpdemo.utils.okhttp.request;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Request;

/**
 * @author: fanyuzeng
 * @date: 2017/10/27 14:08
 * @desc: response for build various kind of {@link okhttp3.Request} include Get Post upload etc.
 */
public class CommonRequest {
    private static final String TAG = "CommonRequest";
    /**
     * create a Get request
     *
     * @param baseUrl base url
     * @param params see {@link RequestParams}
     * @return {@link Request}
     * @created at 2017/10/27 14:39
     */
    public static Request createGetRequest(@NonNull String baseUrl, @Nullable RequestParams params) {
        StringBuilder urlBuilder = new StringBuilder(baseUrl).append("?");
        if (params != null) {
            //将请求参数合并进url中
            for (Map.Entry<String, String> entry : params.getUrlParams().entrySet()) {
                urlBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }

            Log.d(TAG,">> createGetRequest >> " + urlBuilder.toString());
        }
        return new Request.Builder().get().url(urlBuilder.substring(0, urlBuilder.length() - 1)).build();
    }

    /**
     * create a post request
     *
     * @param baseUrl base url
     * @param params see {@link RequestParams}
     * @return {@link Request}
     * @created at 2017/10/27 14:39
     */
    public static Request createPostRequest(@NonNull String baseUrl, @NonNull RequestParams params) {
        FormBody.Builder mFormBodyBuilder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : params.getUrlParams().entrySet()) {
            mFormBodyBuilder.add(entry.getKey(), entry.getValue());
        }
        FormBody formBody = mFormBodyBuilder.build();
        return new Request.Builder().post(formBody).url(baseUrl).build();
    }

}
