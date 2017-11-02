package com.project.fanyuzeng.mvpdemo.api;

import com.project.fanyuzeng.mvpdemo.response.Translation1;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author:fanyuzeng
 * @date: 2017/11/1 15:53
 * @desc:
 */
public interface PostYoudaoTransRequest {
    /**
     * POST 方式请求有道翻译的数据
     * @param targetSentence 待翻译的内容 i表示的是请求体
     * @return  对发送请求的封装类 Translation1 是服务器端返回的数据类型
     */
    @POST("translate?doctype=json&jsonversion=&type=&keyfrom=&model=&mid=&imei=&vendor=&screen=&ssid=&network=&abtest=")
    @FormUrlEncoded
        Call<Translation1> getCall(@Field("i") String targetSentence);
}
