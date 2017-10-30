package com.project.fanyuzeng.mvpdemo.view;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.project.fanyuzeng.mvpdemo.BaseMvpActivity;
import com.project.fanyuzeng.mvpdemo.R;
import com.project.fanyuzeng.mvpdemo.adapter.LatestNewsAdapter;
import com.project.fanyuzeng.mvpdemo.presenter.LatestNewsPresenter;
import com.project.fanyuzeng.mvpdemo.response.LatestNews;

import java.util.List;

/**
 * @author ZengFanyu on 2017/10/20.
 *         Function:
 */
public class LatestNewsTitleActivity extends BaseMvpActivity {
    private ListView mListView;
    private LatestNewsPresenter mBasePresenter;
    LatestNewsAdapter mAdapter;

    @Override
    protected void beforeInitViews() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.activity_latest_news, null);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        mContentContainer.addView(contentView, lp);
    }

    @Override
    protected void initViews() {
        mBasePresenter = new LatestNewsPresenter(this, LatestNews.class);
        mTipView.setText(LatestNews.class.getSimpleName());
        mListView = bindViewId(R.id.id_list_view);
        Button btnLatestNews = bindViewId(R.id.id_btn_latest_news);
        btnLatestNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBasePresenter.requestServer(null);

            }
        });
    }

    @Override
    protected void afterInitViews() {

    }

    @Override
    public void showDataFromPresenter(Object[] data) {
        List<String> titles = (List<String>) data[0];


        if (mAdapter != null) {
            mAdapter.clear();
            mAdapter = null;
        }

        mAdapter = new LatestNewsAdapter(titles, mContext);
        mListView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        mTipView.setText(LatestNews.class.getSimpleName());
    }
}
