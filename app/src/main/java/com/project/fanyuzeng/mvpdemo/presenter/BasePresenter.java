package com.project.fanyuzeng.mvpdemo.presenter;

import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.project.fanyuzeng.mvpdemo.model.IBaseModel;
import com.project.fanyuzeng.mvpdemo.model.LatestNewsModel;
import com.project.fanyuzeng.mvpdemo.view.IBaseView;

import java.util.HashMap;

/**
 * @author：ZengFanyu .
 * @date：2017/10/20
 * @description: 其他presenter类的基类，实现了基本的presenter层方法
 */
public abstract class BasePresenter<Params, Data> implements IBasePresenter<Params> {

    private static final String TAG = "BasePresenter";
    private IBaseModel mBaseModel;
    private IBaseView mBaseView;
    private Params params;
    private Class<Data> clazz;

    /**
     * 用于接收model层返回的数据，并且进行处理了之后，回调到view层
     *
     * @param data 泛型参数，在子类中指定，JavaBean类型
     * @return void
     * @author ZengFanyu
     * @created at 2017/10/21/021 16:58
     */
    public abstract void serverResponse(Data data);

    BasePresenter(IBaseView baseView, Class<Data> clazz) {
        mBaseView = baseView;
        mBaseModel = new LatestNewsModel(this);
        this.clazz = clazz;

    }

    @Override
    public void requestServer(@Nullable Params param) {
        this.params = param;
        mBaseView.showProgress(true);
        getModel().sendRequestToServer(param);
    }

    @Override
    public void accessSuccess(String responseJson) {
        mBaseView.showProgress(false);
        Gson gson = new Gson();
        Data data = gson.fromJson(responseJson, clazz);
//        if (errorNum == 0) {
//        Log.d(TAG, ">> accessSuccess >> " + mResponse.data.toString());
        serverResponse(data);
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
