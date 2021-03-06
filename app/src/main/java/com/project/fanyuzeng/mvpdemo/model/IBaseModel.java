package com.project.fanyuzeng.mvpdemo.model;

/**
 * @author：ZengFanyu
 * Function:
 */
public interface IBaseModel {

    /**
     * 向服务器请求数据的方法
     *
     * @author zfy
     * @created at 2017/10/21/021 14:48
     */
    void sendRequestToServer();

    /**
     * 设置请求url地址的接口
     *
     * @param url 请求地址
     * @author zfy
     * @created at 2017/10/21/021 14:49
     */
    void setRequestUrl(String url);

    /**
     * 设置请求方式的接口，get post。。
     *
     * @param method 请求方式
     * @author zfy
     * @created at 2017/10/21/021 14:50
     */
    void setRequestMethod(int method);

    /**
     * 取消请求的接口
     *
     * @author zfy
     * @created at 2017/10/21/021 14:50
     */
    void cancelRequest();


}
