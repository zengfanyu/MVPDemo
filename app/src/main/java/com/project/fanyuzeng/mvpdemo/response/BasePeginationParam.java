package com.project.fanyuzeng.mvpdemo.response;

/**
 * Created by fanyuzeng on 2017/10/23.
 * Function:
 */
public class BasePeginationParam {
    private int pageIndex;
    private int pageSize;

    public BasePeginationParam() {
    }

    public BasePeginationParam(int pageIndex, int pageSize) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
