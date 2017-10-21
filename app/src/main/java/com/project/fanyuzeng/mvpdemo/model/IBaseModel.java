package com.project.fanyuzeng.mvpdemo.model;

/**
 * Created by fanyuzeng on 2017/10/20.
 * Function:
 */
public interface IBaseModel {

    /**
     * @Description 向服务器请求数据的方法
     * @author zfy
     * @created at 2017/10/21/021 14:48
     */
    void sendRequestToServer();

    /**
     * @param url 请求地址
     * @Description 设置请求url地址的接口
     * @author zfy
     * @created at 2017/10/21/021 14:49
     */
    void setRequestUrl(String url);

    /**
     * @param method 请求方式
     * @Description 设置请求方式的接口，get post。。
     * @author zfy
     * @created at 2017/10/21/021 14:50
     */
    void setMethod(int method);

    /**
     * @Description 取消请求的接口
     * @author zfy
     * @created at 2017/10/21/021 14:50
     */
    void cancelRequest();


}
