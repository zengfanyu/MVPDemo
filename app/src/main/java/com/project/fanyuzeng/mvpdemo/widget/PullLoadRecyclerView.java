package com.project.fanyuzeng.mvpdemo.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.project.fanyuzeng.mvpdemo.R;


/**
 * Created by fanyuzeng on 2017/9/25.
 * Function:下拉刷新和上拉加载的RecyclerView
 */

public class PullLoadRecyclerView extends LinearLayout {
    private static final String TAG = "PullLoadRecyclerView";
    private Context mContext;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private boolean mIsRefresh = false; //是否是刷新
    private boolean mIsLoadMore = false; //是否是加载更多
    private RecyclerView mRecyclerView;
    private OnPullLoadMoreListener mOnPullLoadMoreListener;

    public PullLoadRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public PullLoadRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public PullLoadRecyclerView(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
        View view = LayoutInflater.from(mContext).inflate(R.layout.pull_load_layout, null);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.id_swipe_layout);
        //设置刷新时控件颜色渐变
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_green_dark, android.R.color.holo_blue_dark, android.R.color.holo_orange_dark);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayoutOnRefresh());

        //处理RecyclerView
        mRecyclerView = (RecyclerView) view.findViewById(R.id.id_recycler_view);
        mRecyclerView.setHasFixedSize(true); //设置固定大小
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());//使用默认动画
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext,VERTICAL));
        mRecyclerView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {  //当正在刷新或者是正在加载跟过的时候，让RV消耗触摸事件
                return mIsRefresh || mIsLoadMore;
            }
        });

        mRecyclerView.setVerticalScrollBarEnabled(false);//隐藏滚动条
        mRecyclerView.addOnScrollListener(new RecyclerViewOnScroll());//监听RV的滑动状态
        //view 包含swipeRefreshLayout, RecyclerView, FootView
        this.addView(view);//
    }

    //外部可以设置recyclerview的列数
    public void setLinearLayout() {
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        if (adapter != null) {
            mRecyclerView.setAdapter(adapter);
        }
    }

    private class SwipeRefreshLayoutOnRefresh implements SwipeRefreshLayout.OnRefreshListener {

        @Override
        public void onRefresh() {
            if (!mIsRefresh) {
                mIsRefresh = true;
                refreshData();
            }
        }
    }

    class RecyclerViewOnScroll extends RecyclerView.OnScrollListener {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int firstItem = 0;
            int lastItem = 0;
            RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
            //Returns the number of items in the adapter bound to the parent RecyclerView.
            int totalItemCount = manager.getItemCount();
            //Return the current number of child views attached to the parent RecyclerView.
            // This does not include child views that were temporarily detached and/or scrapped.
            int totalChildCount = manager.getChildCount();
            if (manager instanceof LinearLayoutManager) {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) manager;
                //第一个完全可见的item
                firstItem = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
                //最后一个完全可见的item
                lastItem = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                if (firstItem == 0 || firstItem == RecyclerView.NO_POSITION) {
                    lastItem = linearLayoutManager.findLastVisibleItemPosition();
                }
            }

            Log.d(TAG, "onScrolled " + "firstItem:" + firstItem + ",lastItem:" + lastItem + ",totalItemCount:" + totalItemCount + ",totalChildCount:" + totalChildCount);
            //什么时候触发上拉加载更多?
            // 1.加载更多是false
            // 2.totalItemCount - 1 === lastItem
            // 3.mSwipeRefreshLayout可以用
            // 4. 不是处于下拉刷新状态
            // 5. 偏移量dx > 0 或dy > 0
            if (!mIsLoadMore
                    && totalItemCount - 1 == lastItem
                    && mSwipeRefreshLayout.isEnabled()
                    && !mIsRefresh
                    && (dx > 0 || dy > 0)) {
                mIsLoadMore = true;
                //在加载更多时,禁止mSwipeRefreshLayout使用
                mSwipeRefreshLayout.setEnabled(false);
                loadMoreData();
            } else {
                mSwipeRefreshLayout.setEnabled(true);
            }
        }
    }

    private void refreshData() {
        if (mOnPullLoadMoreListener != null) {
            mOnPullLoadMoreListener.onRefresh();
        }
    }

    private void loadMoreData() {
        if (mOnPullLoadMoreListener != null) {
            //translationY则是View当前位置相对于初始化位置的偏移量

            invalidate();
            mOnPullLoadMoreListener.onLoadMore();
        }
    }

    //设置刷新完毕
    public void setRefreshCompleted() {
        mIsRefresh = false;
        setRefreshing(false);
        Toast.makeText(mContext, "已刷新", Toast.LENGTH_SHORT).show();
    }

    //设置是否正在刷新
    private void setRefreshing(final boolean isRefreshing) {
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(isRefreshing);
            }
        });
    }

    public void setLoadMoreCompleted() {
        mIsLoadMore = false;
        mIsRefresh = false;
        setRefreshing(false);
        //加载结束后
        Toast.makeText(mContext, "已加载到更多", Toast.LENGTH_SHORT).show();
    }

    public interface OnPullLoadMoreListener {
        void onRefresh();

        void onLoadMore();
    }

    public void setOnPullLoadMoreListener(OnPullLoadMoreListener listener) {
        mOnPullLoadMoreListener = listener;
    }
}
