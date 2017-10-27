package com.project.fanyuzeng.mvpdemo.utils.okhttp.listener;

import com.project.fanyuzeng.mvpdemo.utils.okhttp.exception.OkHttpException;

/**
 * @author:fanyuzeng
 * @date: 2017/10/27 13:52
 * @desc: 代理模式,使用DisposeDataHandler 代理 DisposeDataListener的操作
 */
public class DisposeDataHandler {

    public DisposeDataListener mListener;
    public Class<?> mClass;

    public DisposeDataHandler(DisposeDataListener listener) {
        mListener = listener;
    }

    public DisposeDataHandler(DisposeDataListener listener, Class<?> aClass) {
        mListener = listener;
        mClass = aClass;
    }

    public void onSuccess(Object responseObj) {
        mListener.onSuccess(responseObj);
    }

    public void onFailure(OkHttpException exception) {
        mListener.onFailure(exception);
    }

    public Class<?> getClassType() {
        return mClass;
    }
}
