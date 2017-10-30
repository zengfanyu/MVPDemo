package com.project.fanyuzeng.mvpdemo;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.project.fanyuzeng.mvpdemo.view.IBaseView;

/**
 * @author:fanyuzeng
 * @date: 2017/10/30 13:50
 * @desc:
 */
public abstract class BaseMvpActivity extends AppCompatActivity implements IBaseView {
    private static final String TAG = "BaseMvpActivity";
    protected Toolbar mToolbar;
    protected ProgressBar mProgressBar;
    protected TextView mTipView;
    protected FrameLayout mContentContainer;
    protected Handler mHandler = new Handler(Looper.getMainLooper());
    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_mvp);
        mContext = this;
        mToolbar = bindViewId(R.id.id_tool_bar);
        mProgressBar = bindViewId(R.id.id_progress_bar);
        mTipView = bindViewId(R.id.id_tip_content);
        mContentContainer = bindViewId(R.id.id_content_container);
        beforeInitViews();
        initViews();
        afterInitViews();
    }


    protected <T extends View> T bindViewId(int resId) {
        return (T) findViewById(resId);
    }

    protected void setSupportActionBar() {

        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
    }

    protected void setActionBarIcon(int resId) {
        if (mToolbar != null) {
            mToolbar.setNavigationIcon(resId);
        }
    }

    protected void setSupportArrowActionBar(boolean isSupport) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(isSupport);
    }

    @Override
    public void showProgress(final boolean isShow) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (isShow) {
                    mProgressBar.setVisibility(View.VISIBLE);
                } else {
                    mProgressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void showOkHttpError(final int errorCode, final String errorDesc, final String errorUrl) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mTipView.setText("errorCode:" + errorCode + ",errorDesc:" + errorDesc + ",errorUrl:" + errorUrl);
                Log.d(TAG, ">> showOkHttpError >> " + "errorCode:" + errorCode + ",errorDesc:" + errorDesc + ",errorUrl:" + errorUrl);
            }
        });
    }

    @Override
    public void showServerError(final int errorCode, final String errorDesc) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mTipView.setText("errorCode:" + errorCode + ",errorDesc:" + errorDesc);
                Log.d(TAG, ">> showServerError >> " + "errorCode:" + errorCode + ",errorDesc:" + errorDesc);
            }
        });

    }

    @Override
    public void showSuccess(final boolean isSuccess) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (isSuccess) {
                    mContentContainer.setBackgroundResource(android.R.color.white);
                } else {
                    mContentContainer.setBackgroundResource(R.color.colorAccent);
                }

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 子类实现,用于初始化控件
     */
    protected abstract void initViews();

    /**
     * 子类实现 在初始化控件之后进行的操作
     */
    protected abstract void afterInitViews();

    /**
     * 子类实现, 在初始化控件之前的操作
     */
    protected abstract void beforeInitViews();

}
