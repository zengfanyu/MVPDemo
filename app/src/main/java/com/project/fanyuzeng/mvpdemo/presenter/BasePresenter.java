package com.project.fanyuzeng.mvpdemo.presenter;

import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.project.fanyuzeng.mvpdemo.model.BaseModel;
import com.project.fanyuzeng.mvpdemo.model.IBaseModel;
import com.project.fanyuzeng.mvpdemo.response.BaseResponse;
import com.project.fanyuzeng.mvpdemo.view.IBaseView;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by fanyuzeng on 2017/10/20.
 * Function:
 */
public abstract class BasePresenter<Params, Data> implements IBasePresenter<Params> {

    private static final String TAG = "BasePresenter";
    private IBaseModel mBaseModel;
    private IBaseView mBaseView;
    private Params params;
    private Class<Data> clazz;


    public abstract void serverResponse(Data data);

    public BasePresenter(IBaseView baseView, Class<Data> clazz) {
        mBaseView = baseView;
        mBaseModel = new BaseModel(this);
        this.clazz = clazz;

    }

    @Override
    public void requestServer(@Nullable Params param) {
        this.params = param;
        mBaseView.showProgress(true);
        getModel().sendRequestToServer();
    }

    @Override
    public void requestSuccess(JSONObject response) {
        mBaseView.showProgress(false);
        Gson gson = new Gson();
        BaseResponse<Data> mResponse=gson.fromJson(String.valueOf(response)
                ,new TypeToken<BaseResponse<Data>>() {}.getType());

     /*   if (clazz != null) {
            ParameterizedType parameterized = ClassTypeUtil.type(BaseResponse.class, clazz);
            Type type = $Gson$Types.canonicalize(parameterized);
            mResponse = gson.fromJson(String.valueOf(response), type);
        } else {
            mResponse = gson.fromJson(String.valueOf(response), BaseResponse.class);
        }*/


        /**
         * 在实际设计系统的时候，通过状态码来判断服务器是否正确响应
         * 如果响应错误，可以在这里直接通知view层错误情况
         * 以下为根据百度api的数据格式设计的回调处理
         * errorNum = 0 时，响应成功
         */

//        if (mResponse.errorNum == 0) {
        Log.d(TAG,">> requestSuccess >> " + mResponse.data.toString());
        // TODO: 2017/10/20 传递到了此处
        serverResponse(mResponse.data);
            mBaseView.showSuccess(true);
//        } else {
//            mBaseView.showServerError(mResponse.errorNum, mResponse.errorMsg);
//        }

    }

    @Override
    public void cancelRequest() {
        mBaseModel.cancelRequest();
    }

    @Override
    public void okHttpError(int errorCode, String errorDesc, String errorUrl) {
        mBaseView.showOkHttpError(errorCode, errorDesc, errorUrl);
    }

    @Override
    public IBaseModel getModel() {
        return mBaseModel;
    }

    @Override
    public HashMap<String, String> getParams() {
        return null;
    }
}
