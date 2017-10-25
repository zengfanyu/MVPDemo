package com.project.fanyuzeng.mvpdemo.presenter;

/**
 * @author：ZengFanyu
 * Function:在IBasePresenter的基础上扩展的接口，适用于分页加载的情况
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

    /**
     * 用于判断服务器端是否还有更多的数据
     * @return true -还有更多数据 - false 没有更多的数据
     */
    boolean hasMoreData();

}
