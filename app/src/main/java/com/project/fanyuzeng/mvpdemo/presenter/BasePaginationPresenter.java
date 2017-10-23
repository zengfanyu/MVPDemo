package com.project.fanyuzeng.mvpdemo.presenter;

import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.project.fanyuzeng.mvpdemo.Constants;
import com.project.fanyuzeng.mvpdemo.model.IBaseModel;
import com.project.fanyuzeng.mvpdemo.model.SohuAlbumModel;
import com.project.fanyuzeng.mvpdemo.response.BasePeginationParam;
import com.project.fanyuzeng.mvpdemo.view.IBaseListView;

import java.util.HashMap;

/**
 * Created by fanyuzeng on 2017/10/23.
 * Function:
 */
public abstract class BasePaginationPresenter<Param extends BasePeginationParam, Data> implements IBasePaginationPresenter<Param> {
    private IBaseModel mBaseModel;
    private IBaseListView mBaseListView;
    private Param mParam;
    private Class<Data> mClazz;
    private boolean mIsFromRefresh=false;


    //一次加载数据条数
    private int mCount = Constants.COUNT;

    private int mPageIndex = 0;

    private int mPageSize = 5;

//    private List<Data> mDatas = new ArrayList<>();

    public abstract void serverResponse(Data data);


    public BasePaginationPresenter(IBaseListView baseListView, Class<Data> Clazz) {
        this.mBaseListView = baseListView;
        mClazz = Clazz;
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
    public void setCount(int count) {
        this.mPageSize = count;
    }


    @Override
    public void requestServer(@Nullable Param param) {
        mBaseListView.showProgress(true);
        //如果上一次的请求还没有完成， 则先取消上一次的请求
//        cancelRequest();
        if (param != null) {
//            param.setPageIndex(param.getPageIndex());
            mParam = param;
            getModel().sendRequestToServer(param);
        }
    }

    @Override
    public void accessSuccess(String responseJson) {
        mBaseListView.showProgress(false);
        Gson gson = new Gson();
        if (!mIsFromRefresh) {
            serverResponse(gson.fromJson(responseJson, mClazz));
        }


    }


    @Override
    public void cancelRequest() {
        mBaseModel.cancelRequest();
    }

    @Override
    public void okHttpError(int errorCode, String errorDesc, String errorUrl) {
        mBaseListView.showOkHttpError(errorCode, errorDesc, errorUrl);
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
