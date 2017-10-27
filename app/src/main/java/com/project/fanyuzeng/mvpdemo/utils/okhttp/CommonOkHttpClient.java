package com.project.fanyuzeng.mvpdemo.utils.okhttp;

import com.project.fanyuzeng.mvpdemo.utils.okhttp.listener.DisposeDataHandler;
import com.project.fanyuzeng.mvpdemo.utils.okhttp.response.CommonJsonCallback;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * @author:fanyuzeng
 * @date: 2017/10/27 15:21
 * @desc:
 */
public class CommonOkHttpClient {
    private static final int TIME_OUT = 30;

    private static OkHttpClient sOkHttpClient;

    static {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        builder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        builder.readTimeout(TIME_OUT, TimeUnit.SECONDS);
        builder.writeTimeout(TIME_OUT, TimeUnit.SECONDS);
        //允许重定向
        builder.followRedirects(true);
        // TODO: 2017/10/27 https
        sOkHttpClient = builder.build();
    }

    public static void post(Request request, DisposeDataHandler handler) {
        sOkHttpClient.newCall(request).enqueue(new CommonJsonCallback(handler));
    }

    public static void get(Request request, DisposeDataHandler handler) {
        sOkHttpClient.newCall(request).enqueue(new CommonJsonCallback(handler));
    }
}
