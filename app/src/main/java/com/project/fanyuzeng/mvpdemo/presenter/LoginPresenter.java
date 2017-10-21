package com.project.fanyuzeng.mvpdemo.presenter;

import android.text.TextUtils;

import com.project.fanyuzeng.mvpdemo.model.ILoginModel;
import com.project.fanyuzeng.mvpdemo.model.LoginModel;
import com.project.fanyuzeng.mvpdemo.view.ILoginView;

/**
 * Created by fanyuzeng on 2017/10/19.
 * Function:
 */

public class LoginPresenter implements ILoginPresenter {
    private ILoginView mLoginView;

    private ILoginModel mLoginModel;

    public LoginPresenter(ILoginView ILoginView) {
        mLoginView = ILoginView;
        mLoginModel = new LoginModel(this);
    }

    @Override
    public void loginToServer() {
        String userName = mLoginView.getUserName();
        String password = mLoginView.getUserPassword();
        if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(password)) {
            mLoginView.showLoading(true);
            mLoginModel.login(userName, password);
        }
    }

    @Override
    public void loginSuccess() {
        mLoginView.showLoading(false);
        mLoginView.showLoginSuccessView();

    }

    @Override
    public void loginFailed() {
        mLoginView.showLoading(false);
        mLoginView.showLoginFailedView();

    }
}
