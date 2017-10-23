package com.project.fanyuzeng.mvpdemo.presenter;

/**
 * Created by fanyuzeng on 2017/10/23.
 * Function:
 */
public interface IBasePaginationPresenter<Param> extends IBasePresenter<Param> {

    void refresh(Param param);

    void loadingNext();

    void setCount(int count);




}
