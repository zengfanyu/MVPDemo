package com.project.fanyuzeng.mvpdemo.utils.okhttp;

import com.project.fanyuzeng.mvpdemo.Constants;
import com.project.fanyuzeng.mvpdemo.utils.okhttp.listener.DisposeDataHandler;
import com.project.fanyuzeng.mvpdemo.utils.okhttp.request.CommonRequest;
import com.project.fanyuzeng.mvpdemo.utils.okhttp.request.RequestParams;
import com.project.fanyuzeng.mvpdemo.utils.okhttp.response.CommonJsonCallback;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * @author:fanyuzeng
 * @date: 2017/10/27 17:57
 * @desc:
 */
public class OkHttpManager {
    private static volatile OkHttpManager sManager;
    private  OkHttpClient mOkHttpClient;

    private OkHttpManager() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        builder.connectTimeout(Constants.HTTP_TIME_OUT, TimeUnit.SECONDS);
        builder.readTimeout(Constants.HTTP_TIME_OUT, TimeUnit.SECONDS);
        builder.writeTimeout(Constants.HTTP_TIME_OUT, TimeUnit.SECONDS);
        //允许重定向
        builder.followRedirects(true);
        // TODO: 2017/10/27 https
        mOkHttpClient = builder.build();
    }


    public static OkHttpManager getInstance() {
        if (sManager == null) {
            synchronized (OkHttpManager.class) {
                if (sManager == null) {
                    sManager = new OkHttpManager();
                }
            }
        }
        return sManager;
    }

    /**
     * 使用{@link OkHttpClient}想服务器端请求数据的方法
     * @param method {@link Constants#HTTP_GET_METHOD} Get方式,{@link Constants#HTTP_POST_METHOD} Post方式
     * @param baseUrl baseUrl
     * @param paramsMap 请求url的参数,以键值对的形式存放
     * @param handler
     */
    public void requestServerData(int method, String baseUrl, HashMap<String, String> paramsMap, DisposeDataHandler handler) {
        RequestParams requestParams = new RequestParams(paramsMap);
        Request request = null;
        if (method == Constants.HTTP_GET_METHOD) {
            request = CommonRequest.createGetRequest(baseUrl, requestParams);
        } else if (method == Constants.HTTP_POST_METHOD) {
            request = CommonRequest.createPostRequest(baseUrl, requestParams);
        }
        if (request != null) {
            mOkHttpClient.newCall(request).enqueue(new CommonJsonCallback(handler));
        }


    }


}
