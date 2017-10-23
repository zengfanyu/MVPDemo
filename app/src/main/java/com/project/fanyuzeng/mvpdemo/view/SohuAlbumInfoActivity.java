package com.project.fanyuzeng.mvpdemo.view;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.project.fanyuzeng.mvpdemo.PullLoadRecyclerView;
import com.project.fanyuzeng.mvpdemo.R;
import com.project.fanyuzeng.mvpdemo.adapter.VideoInfoAdapter;
import com.project.fanyuzeng.mvpdemo.presenter.AlbumPresenter;
import com.project.fanyuzeng.mvpdemo.response.Album;
import com.project.fanyuzeng.mvpdemo.response.BasePeginationParam;
import com.project.fanyuzeng.mvpdemo.response.VideoInfo;

import java.util.List;

/**
 * Created by fanyuzeng on 2017/10/23.
 * Function:
 */
public class SohuAlbumInfoActivity extends AppCompatActivity implements ISOHUSerials {
    private static final String TAG = "SohuAlbumInfoActivity";
    private PullLoadRecyclerView mRecyclerView;
    private Context mContext;
    private ProgressBar mProgressBar;
    private TextView mTip;
    private TextView mErrorContent;
    private RelativeLayout mSuccessContent;
    private AlbumPresenter mAlbumPresenter;
    private BasePeginationParam mParam;
    private VideoInfoAdapter mAdapter;
    Handler mHandler = new Handler(Looper.getMainLooper());
    private boolean mIsFromRefresh = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_view);
        mContext = this;

        mAlbumPresenter = new AlbumPresenter(this, Album.class);

        mParam = new BasePeginationParam(1, 10); //此处值可以有用户输入或者其他途径获得

        mSuccessContent = (RelativeLayout) findViewById(R.id.id_success_content);
        mErrorContent = (TextView) findViewById(R.id.id_error_content);
        mTip = (TextView) findViewById(R.id.id_tip);
        mProgressBar = (ProgressBar) findViewById(R.id.id_progress_bar);


        mRecyclerView = (PullLoadRecyclerView) findViewById(R.id.id_recycler_view);
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
                Log.d(TAG, ">> onRefresh >> " + "param.index:" + mParam.getPageIndex() + ",param.size:" + mParam.getPageSize());
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
    public void showMainInfo(List<VideoInfo> albumList) {
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

                }
            });

        }
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
                mTip.setText("http err:" + "errCode:" + errorCode + ",errDesc:" + errorDesc + ",errUrl:" + errorUrl);

            }
        });
    }

    @Override
    public void showServerError(final int errorCode, final String errorDesc) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mTip.setText("server err:" + "errCode:" + errorCode + ",errDesc:" + errorDesc);

            }
        });
    }

    @Override
    public void showSuccess(boolean isSuccess) {
        if (isSuccess) {
            mSuccessContent.setVisibility(View.VISIBLE);
            mErrorContent.setVisibility(View.GONE);

        } else {
            mSuccessContent.setVisibility(View.GONE);
            mErrorContent.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void refreshLayout(List<VideoInfo> albumList) {
        mAdapter = null;
        mAdapter = new VideoInfoAdapter(mContext, albumList);
        mRecyclerView.setLinearLayout();
        mRecyclerView.setAdapter(mAdapter);
        Toast.makeText(mContext, "已刷新", Toast.LENGTH_SHORT).show();
    }
}
