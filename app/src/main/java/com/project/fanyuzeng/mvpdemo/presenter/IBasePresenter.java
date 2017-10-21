package com.project.fanyuzeng.mvpdemo.presenter;

import android.support.annotation.Nullable;

import com.project.fanyuzeng.mvpdemo.model.IBaseModel;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by fanyuzeng on 2017/10/20.
 * Function:
 */
public interface IBasePresenter<Param> {


    void requestServer(@Nullable Param param);

    void requestSuccess(JSONObject response);

    void cancelRequest();

    void okHttpError(int errorCode, String errorDesc, String errorUrl);

    IBaseModel getModel();

    HashMap<String, String> getParams();
}
