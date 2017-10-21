package com.project.fanyuzeng.mvpdemo;

import android.app.Application;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;

/**
 * Created by fanyuzeng on 2017/10/20.
 * Function:
 */
public class AppManager extends Application {
    private static OkHttpClient sClient;
    private static Gson sGson;

    @Override
    public void onCreate() {
        super.onCreate();
        sClient = new OkHttpClient();
        sGson = new Gson();
    }


    public static OkHttpClient getOkHttpClient() {
        if (sClient != null) {
            return sClient;
        }
        return null;
    }

    public static Gson getGson() {
        if (sGson != null) {
            return sGson;
        }
        return null;
    }

}
