package com.project.fanyuzeng.mvpdemo.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.project.fanyuzeng.mvpdemo.R;
import com.project.fanyuzeng.mvpdemo.presenter.LatestNewsPresenter;

/**
 * Created by fanyuzeng on 2017/10/20.
 * Function:
 */
public class LatestViewActivity extends AppCompatActivity  {
    private ListView mListView;
    private Context mContext;
    private Button mBtnLatestNews;
    private LatestNewsPresenter mLatestNewsPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latest_news);
        mContext = this;
//        mLatestNewsPresenter = new LatestNewsPresenter(this);
        mListView = (ListView) findViewById(R.id.id_list_view);
        mBtnLatestNews = (Button) findViewById(R.id.id_btn_latest_news);
        mBtnLatestNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLatestNewsPresenter.getDataFromServer();
            }
        });

    }
//
//    @Override
//    public void showLatestViewTitle(List<String> titles) {
//        if (titles != null && titles.size() != 0) {
//            LatestNewsAdapter adapter = new LatestNewsAdapter(titles, mContext);
//                mListView.setAdapter(adapter);
//        }
//    }

}
