package com.project.fanyuzeng.mvpdemo.utils.okhttp.request;

import android.text.TextUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author:fanyuzeng
 * @date: 2017/10/27 13:55
 * @desc: 封装url中的参数
 */
public class RequestParams {
    /**
     * 使用{@link ConcurrentHashMap}是为了保证线程安全
     */
    private ConcurrentHashMap<String, String> urlParams = new ConcurrentHashMap<>();

    public RequestParams() {
    }

    public RequestParams(Map<String, String> source) {
        for (Map.Entry<String, String> entry : source.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    public RequestParams(String key, String value) {
        put(key, value);

    }

    private void put(String key, String value) {
        if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(value)) {
            urlParams.put(key, value);
        }
    }

    public ConcurrentHashMap<String, String> getUrlParams() {
        return urlParams;
    }


}
