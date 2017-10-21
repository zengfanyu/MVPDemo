package com.project.fanyuzeng.mvpdemo.view;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.project.fanyuzeng.mvpdemo.R;
import com.project.fanyuzeng.mvpdemo.adapter.LatestNewsAdapter;
import com.project.fanyuzeng.mvpdemo.presenter.LatesNewsFromBase;
import com.project.fanyuzeng.mvpdemo.response.LatestNews;

import java.util.List;

/**
 * Created by fanyuzeng on 2017/10/20.
 * Function:
 */
public class BaseViewActivity extends AppCompatActivity implements ILatestNewsView {
    private ListView mListView;
    private Context mContext;
    private Button mBtnLatestNews;
    private ProgressBar mProgressBar;
    private TextView mTip;
    private TextView mErrorContent;
    private RelativeLayout mSuccesscontent;
    private LatesNewsFromBase mBasePresenter;
    Handler mHandler=new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_view);
        mContext = this;

        mBasePresenter = new LatesNewsFromBase(this, LatestNews.class);


        mSuccesscontent = (RelativeLayout) findViewById(R.id.id_success_content);
        mErrorContent = (TextView) findViewById(R.id.id_error_content);
        mTip = (TextView) findViewById(R.id.id_tip);
        mListView = (ListView) findViewById(R.id.id_list_view);
        mProgressBar = (ProgressBar) findViewById(R.id.id_progress_bar);
        mBtnLatestNews = (Button) findViewById(R.id.id_btn_latest_news);
        mBtnLatestNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBasePresenter.requestServer(null);

            }
        });
    }

    @Override
    public void showProgress(final boolean isShow) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (isShow)
                    mProgressBar.setVisibility(View.VISIBLE);
                else
                    mProgressBar.setVisibility(View.GONE);
            }
        });


    }

    @Override
    public void showOkHttpError(int errorCode, String errorDesc, String errorUrl) {
        mTip.setText("http err:" + "errCode:" + errorCode + ",errDesc:" + errorDesc + ",errUrl:" + errorUrl);
    }

    @Override
    public void showServerError(int errorCode, String errorDesc) {
        mTip.setText("server err:" + "errCode:" + errorCode + ",errDesc:" + errorDesc);

    }

    @Override
    public void showSuccess(boolean isSuccess) {
        if (isSuccess) {
            mSuccesscontent.setVisibility(View.VISIBLE);
            mErrorContent.setVisibility(View.GONE);
            mBtnLatestNews.setVisibility(View.VISIBLE);

        } else {
            mSuccesscontent.setVisibility(View.GONE);
            mErrorContent.setVisibility(View.VISIBLE);
            mBtnLatestNews.setVisibility(View.GONE);
        }
    }

    @Override
    public void showLatestViewTitle(List<String> titles) {
        if (titles != null && titles.size() != 0) {
            LatestNewsAdapter adapter = new LatestNewsAdapter(titles, mContext);
            mListView.setAdapter(adapter);
        }
    }
}
