package com.project.fanyuzeng.mvpdemo.utils.okhttp.listener;

import com.project.fanyuzeng.mvpdemo.utils.okhttp.exception.OkHttpException;

/**
 * @author:fanyuzeng
 * @date: 2017/10/27 13:49
 * @desc:
 */
public interface DisposeDataListener {
    /**
     * 请求服务器数据成功时回调的方法
     *
     * @param responseObj 需要回调到上层的请求结果
     */
    void onSuccess(Object responseObj);

    /**
     * 请求服务器失败时候的回调方法
     *
     * @param exception 需要回调到上层的错误反馈
     */
    void onFailure(OkHttpException exception);

}
