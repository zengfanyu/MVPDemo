package com.project.fanyuzeng.mvpdemo.view;

/**
 * Created by fanyuzeng on 2017/10/19.
 * Function:
 */

public interface ILoginView {


    String getUserName();

    String getUserPassword();


    void showLoading(boolean isShow);

    void showLoginSuccessView();

    void showLoginFailedView();




}
