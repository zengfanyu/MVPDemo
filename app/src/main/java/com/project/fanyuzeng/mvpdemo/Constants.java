package com.project.fanyuzeng.mvpdemo;

/**
 * Created by fanyuzeng on 2017/10/20.
 * Function:
 */
public class Constants {

    public static final String BASE_URL = "https://news-at.zhihu.com/api/4/";
    public static final String LATEST_NEWS = BASE_URL + "news/latest";
    public static final int URL_ERROR = 1;
    public static final int SERVER_ERROR = 2;
    public static final String SOHU_SERIALS_URL_BASE = "http://api.tv.sohu.com/v4/search/channel.json";


    public static final String SOHU_SERIALS_URL = "http://api.tv.sohu.com/v4/search/channel.json" +
            "?cid=2&o=1&plat=6&poid=1&api_key=9854b2afa779e1a6bff1962447a09dbd&" +
            "sver=6.2.0&sysver=4.4.2&partner=47&page=%s&page_size=%s";
    public static final int COUNT = 10;
    public static final int HTTP_GET_METHOD = 1;
    public static final int HTTP_POST_METHOD = 2;
    public static final int HTTP_TIME_OUT=30;

    public static final String RETROFIT_SOHU_BASE_URL="http://api.tv.sohu.com";

}
