package com.project.fanyuzeng.mvpdemo.api;

import com.project.fanyuzeng.mvpdemo.response.Album;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * @author:fanyuzeng
 * @date: 2017/11/1 15:31
 * @desc:
 */
public interface GetSohuSerialsRequest {
    /**
     * Get方式请求搜狐电视剧频道信息的接口 {@link retrofit2.Retrofit} 需要
     * @return 对发送请求的封装类 Album 是服务器端返回的数据类型
     */
    @GET("/v4/search/channel.json")
 Call<Album> getSohuSerialsData(@QueryMap Map<String,String> params);
}
