package com.project.fanyuzeng.mvpdemo.presenter;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;
import com.project.fanyuzeng.mvpdemo.model.IBaseModel;
import com.project.fanyuzeng.mvpdemo.model.SohuAlbumModel;
import com.project.fanyuzeng.mvpdemo.response.BasePaginationParam;
import com.project.fanyuzeng.mvpdemo.view.IBaseView;

import java.util.HashMap;

/**
 * @author：ZengFanyu
 * @date：2017/10/20
 */
public abstract class BasePaginationPresenter<Param extends BasePaginationParam, Data> implements IBasePaginationPresenter<Param> {
    private static final String TAG = "BasePaginationPresenter";
    private IBaseModel mBaseModel;
    private IBaseView mBaseListView;
    protected Param mParam;
    private Class<Data> mClazz;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    /**
     * 子类中调用，用于传递服务器返回的，处理好的结果
     *
     * @param data View层需要的数据类型
     * @created at 2017/10/23 20:10
     */
    public abstract void serverResponse(Data data);

    /**
     * 子类中调用，用于确认服务器端是否还有数据
     *
     * @return true-还有数据 false-没有数据
     */
    public abstract boolean serverHaveMoreData();

    public BasePaginationPresenter(IBaseView baseListView, Class<Data> clazz) {
        this.mBaseListView = baseListView;
        mClazz = clazz;
        mBaseModel = new SohuAlbumModel(this);
    }


    @Override
    public void refresh(Param param) {
        requestServer(param);
    }

    @Override
    public void loadingNext() {
        if (mParam != null) {
            int pageIndex = mParam.getPageIndex();
            mParam.setPageIndex(pageIndex + 1);
            requestServer(mParam);
        }
    }

    @Override
    public void requestServer(@Nullable Param param) {
        mBaseListView.showProgress(true);
        mParam = param;
        Log.d(TAG, ">> requestServer >> ");
        getModel().sendRequestToServer(param);
    }

    @Override
    public void accessSuccess(String responseJson) {
        mBaseListView.showProgress(false);
        Gson gson = new Gson();
        serverResponse(gson.fromJson(responseJson, mClazz));
        mBaseListView.showSuccess(true);
    }


    @Override
    public void cancelRequest() {
        mBaseModel.cancelRequest();
    }

    @Override
    public void okHttpError(final int errorCode, final String errorDesc, final String errorUrl) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mBaseListView.showOkHttpError(errorCode, errorDesc, errorUrl);
                mBaseListView.showProgress(false);
                mBaseListView.showSuccess(false);
            }
        });
    }

    @Override
    public IBaseModel getModel() {
        return mBaseModel;
    }

    @Override
    public HashMap<String, String> getParams() {
        return null;
    }

    @Override
    public boolean hasMoreData() {
        return serverHaveMoreData();
    }
}
