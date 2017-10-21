package com.project.fanyuzeng.mvpdemo.view;

/**
 * Created by fanyuzeng on 2017/10/20.
 * Function:
 */
public interface IBaseView {

    void showProgress(boolean isShow);

    void showOkHttpError(int errorCode, String errorDesc, String errorUrl);

    void showServerError(int errorCode, String errorDesc);

    void showSuccess(boolean isSuccess);

}
