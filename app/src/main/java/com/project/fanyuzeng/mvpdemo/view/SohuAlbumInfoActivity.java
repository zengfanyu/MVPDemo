package com.project.fanyuzeng.mvpdemo.view;

import android.widget.FrameLayout;

import com.project.fanyuzeng.mvpdemo.BaseMvpActivity;
import com.project.fanyuzeng.mvpdemo.adapter.VideoInfoAdapter;
import com.project.fanyuzeng.mvpdemo.presenter.AlbumPresenter;
import com.project.fanyuzeng.mvpdemo.response.Album;
import com.project.fanyuzeng.mvpdemo.response.BasePaginationParam;
import com.project.fanyuzeng.mvpdemo.response.VideoInfo;
import com.project.fanyuzeng.mvpdemo.widget.PullLoadRecyclerView;

import java.util.List;

/**
 * @author：ZengFanyu Function:
 */
public class SohuAlbumInfoActivity extends BaseMvpActivity {
    private static final String TAG = "SohuAlbumInfoActivity";
    private PullLoadRecyclerView mRecyclerView;
    private AlbumPresenter mAlbumPresenter;
    private BasePaginationParam mParam = new BasePaginationParam(1, 10);
    private VideoInfoAdapter mAdapter;
    private boolean mIsFromRefresh = false;

    @Override
    protected void beforeInitViews() {
        mRecyclerView = new PullLoadRecyclerView(this);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        mContentContainer.addView(mRecyclerView, lp);

//        View contentView = LayoutInflater.from(this).inflate(R.layout.activity_album_view, null);
//        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
//        mContentContainer.addView(contentView, lp);
    }

    @Override
    protected void initViews() {
        setSupportActionBar(); //表示当前页面支持ActionBar
        setTitle(TAG);
        setSupportArrowActionBar(true);

        mAlbumPresenter = new AlbumPresenter(this, Album.class);

        mTipView.setText(TAG);

//        mRecyclerView = (PullLoadRecyclerView) findViewById(R.id.id_recycler_view);
        mRecyclerView.setLinearLayout();
        mAdapter = new VideoInfoAdapter(mContext);
        mAlbumPresenter.requestServer(mParam);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setOnPullLoadMoreListener(new PullLoadRecyclerView.OnPullLoadMoreListener() {
            @Override
            public void onRefresh() {
                mIsFromRefresh = true;
                mParam.setPageIndex(1);
                mAlbumPresenter.refresh(mParam);
                mRecyclerView.setRefreshCompleted();
            }

            @Override
            public void onLoadMore() {
                mAlbumPresenter.loadingNext();
                mRecyclerView.setLoadMoreCompleted();
            }
        });
    }

    @Override
    protected void afterInitViews() {

    }

    @Override
    public void showDataFromPresenter(Object[] data) {
        List<VideoInfo> albumList = (List<VideoInfo>) data[0];
        if (mIsFromRefresh) {
            mAdapter.cleanData();
            mIsFromRefresh = false;
        }
        if (albumList != null && albumList.size() > 0) {
            for (VideoInfo videoInfo : albumList) {
                mAdapter.addData(videoInfo);
            }

            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mAdapter.notifyDataSetChanged();
                    mTipView.setText(TAG);
                }
            });

        }
    }
}
