package com.project.fanyuzeng.mvpdemo.view;

/**
 * Created by fanyuzeng on 2017/10/23.
 * Function:
 */
public interface IBaseListView extends IBaseView {
    /**
     * 判断是否还有下一页数据，主要用于在上拉加载前的判断
     *
     * @return true-有数据 false-没数据
     * @created at 2017/10/23 10:21
     */
    boolean hasNextPage();
}
