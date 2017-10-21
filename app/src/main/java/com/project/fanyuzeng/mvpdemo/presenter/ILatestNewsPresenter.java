package com.project.fanyuzeng.mvpdemo.presenter;

/**
 * Created by fanyuzeng on 2017/10/20.
 * Function:
 */
public interface ILatestNewsPresenter {

    void handleData(String jsonData);

    void getDataFromServer();
}
