package com.project.fanyuzeng.mvpdemo.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.project.fanyuzeng.mvpdemo.LoginSuccessActivity;
import com.project.fanyuzeng.mvpdemo.R;
import com.project.fanyuzeng.mvpdemo.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements ILoginView {


    private Button mLoginBtn;
    private EditText mUsernameEt;
    private EditText mPassword;
    private ProgressBar mProgressBar;

    private LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mLoginPresenter=new LoginPresenter(this);
        initViews();
    }

    private void initViews() {
        mLoginBtn = (Button) findViewById(R.id.id_btn_login);
        mUsernameEt = (EditText) findViewById(R.id.id_et_username);
        mPassword = (EditText) findViewById(R.id.id_et_password);
        mProgressBar = (ProgressBar) findViewById(R.id.id_progress_bar);

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLoginPresenter.loginToServer();
            }
        });
    }


    @Override
    public String getUserName() {
        String userName = mUsernameEt.getText().toString();
        if (!TextUtils.isEmpty(userName)) {
            return userName;
        }
        return null;
    }

    @Override
    public String getUserPassword() {
        String passWord = mPassword.getText().toString();
        if (!TextUtils.isEmpty(passWord)) {
            return passWord;
        }
        return null;
    }

    @Override
    public void showLoading(boolean isShow) {
        if (isShow)
            mProgressBar.setVisibility(View.VISIBLE);
        else
            mProgressBar.setVisibility(View.GONE);

    }

    @Override
    public void showLoginSuccessView() {
        Intent intent=new Intent(this, LoginSuccessActivity.class);
        startActivity(intent);
    }

    @Override
    public void showLoginFailedView() {
        Toast.makeText(this,"登录失败",Toast.LENGTH_SHORT).show();
    }


}
