package com.project.fanyuzeng.mvpdemo.model;

import com.project.fanyuzeng.mvpdemo.bean.User;
import com.project.fanyuzeng.mvpdemo.presenter.ILoginPresenter;

/**
 * Created by fanyuzeng on 2017/10/19.
 * Function:
 */

public class LoginModel implements ILoginModel {
    private static final String TAG = "LoginModel";
    private ILoginPresenter mLoginPresenter;

    public LoginModel(ILoginPresenter loginPresenter) {
        mLoginPresenter = loginPresenter;
    }

    @Override
    public void login(final String username, final String password) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if ("zfy".equals(username) && "wzm".equals(password)) {
                    User user = new User();
                    user.setUserName(username);
                    user.setUserName(password);
                    mLoginPresenter.loginSuccess();
                } else {
                    mLoginPresenter.loginFailed();
                }
            }
        }).start();
    }


}
