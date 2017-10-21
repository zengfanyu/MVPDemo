package com.project.fanyuzeng.mvpdemo.model;

/**
 * Created by fanyuzeng on 2017/10/20.
 * Function:
 */
public interface IBaseModel {

    void sendRequestToServer();

    void setRequestUrl(String url);

    void setMethod(int method);

    void cancelRequest();


}
