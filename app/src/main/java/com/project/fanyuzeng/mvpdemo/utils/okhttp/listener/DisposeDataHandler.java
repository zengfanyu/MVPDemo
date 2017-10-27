package com.project.fanyuzeng.mvpdemo.utils.okhttp.listener;

/**
 * @author:fanyuzeng
 * @date: 2017/10/27 13:52
 * @desc:
 */
public class DisposeDataHandler {

    public DisposeDataListener mListener;
    public Class<?>mClass;

    public DisposeDataHandler(DisposeDataListener listener) {
        mListener = listener;
    }

    public DisposeDataHandler(DisposeDataListener listener, Class<?> aClass) {
        mListener = listener;
        mClass = aClass;
    }
}
