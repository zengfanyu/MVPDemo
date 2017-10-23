package com.project.fanyuzeng.mvpdemo.view;

/**
 * Created by fanyuzeng on 2017/10/20.
 * @author : ZengFanyu
 */
public interface IBaseView {
    /**
     * 进行耗时操作时的用户友好交互接口，比如显示ProgressBar
     *
     * @param isShow
     * @author zfy
     * @created at 2017/10/21/021 14:12
     */
    void showProgress(boolean isShow);

    /**
     * 显示网络请求错的的接口
     *
     * @param errorCode
     * @param errorDesc
     * @param errorUrl
     * @author zfy
     * @created at 2017/10/21/021 14:14
     */
    void showOkHttpError(int errorCode, String errorDesc, String errorUrl);

    /**
     * 现实服务器端请求错误的接口
     *
     * @param errorCode
     * @param errorDesc
     * @author zfy
     * @created at 2017/10/21/021 14:14
     */
    void showServerError(int errorCode, String errorDesc);

    /**
     * 请求成功或者失败之后，对应UI做出改变的接口
     *
     * @param isSuccess
     * @author zfy
     * @created at 2017/10/21/021 14:15
     */
    void showSuccess(boolean isSuccess);

}
