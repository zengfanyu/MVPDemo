package com.project.fanyuzeng.mvpdemo.utils.okhttp.exception;

/**
 * @author:fanyuzeng
 * @date: 2017/10/27 13:44
 * @desc:
 */
public class OkHttpException extends Exception {

    private int mErrorCode;
    private String mErrorMsg;

    public OkHttpException(int errorCode, String errorMsg) {
        this.mErrorCode = errorCode;
        this.mErrorMsg = errorMsg;
    }

    public int getErrorCode() {
        return mErrorCode;
    }

    public String getErrorMsg() {
        return mErrorMsg;
    }
}
