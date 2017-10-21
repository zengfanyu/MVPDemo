package com.project.fanyuzeng.mvpdemo.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by fanyuzeng on 2017/10/20.
 * Function:
 */
public class BaseResponse<Data> {

    @SerializedName(value = "error_type", alternate = {"status", "errno", "err_num", "err_type", "errNum"})
    public int errorNum;

    @SerializedName(value = "error_msg", alternate = {"msg", "errMsg"})
    public String errorMsg;

    /**
     * 兼容不同的数据情况
     * stories：最新新闻 top_stories：顶部轮播新闻
     */
    @SerializedName(value = "stories")
    public Data data;
}
