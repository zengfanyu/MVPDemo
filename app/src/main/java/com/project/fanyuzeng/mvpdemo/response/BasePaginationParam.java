package com.project.fanyuzeng.mvpdemo.response;

/**
 * @author：ZengFanyu
 * Function:
 */
public class BasePaginationParam {
    private int pageIndex;
    private int pageSize;

    public BasePaginationParam() {
    }

    public BasePaginationParam(int pageIndex, int pageSize) {
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
