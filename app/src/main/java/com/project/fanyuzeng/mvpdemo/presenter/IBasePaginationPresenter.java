package com.project.fanyuzeng.mvpdemo.presenter;

/**
 * Created by fanyuzeng on 2017/10/23.
 * Function:在IBasePresenter的基础上扩展的接口，试用于分页加载的情况
 */
public interface IBasePaginationPresenter<Param> extends IBasePresenter<Param> {
    /**
     * 刷新数据的接口
     *
     * @param param 访问服务器的参数
     * @created at 2017/10/23 20:07
     */
    void refresh(Param param);

    /**
     * 加载更多的接口
     *
     * @created at 2017/10/23 20:07
     */
    void loadingNext();


}
