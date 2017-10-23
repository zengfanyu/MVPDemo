package com.project.fanyuzeng.mvpdemo.view;

import java.util.List;

/**
 * @author fanyuzeng
 */
public interface ILatestNewsView extends IBaseView {
    /**
     * 展示知乎日报最新新闻标题的接口
     *
     * @param titles 标题名字的集合
     * @author zfy
     * @created at 2017/10/21/021 14:46
     */
    void showLatestViewTitle(List<String> titles);
}
