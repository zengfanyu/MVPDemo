package com.project.fanyuzeng.mvpdemo.presenter;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.project.fanyuzeng.mvpdemo.AppManager;
import com.project.fanyuzeng.mvpdemo.response.LatestNews;
import com.project.fanyuzeng.mvpdemo.model.IRequestLatestModel;
import com.project.fanyuzeng.mvpdemo.model.RequestLatestNewsModel;
import com.project.fanyuzeng.mvpdemo.view.ILatestNewsView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanyuzeng on 2017/10/20.
 * Function:
 */
public class LatestNewsPresenter implements ILatestNewsPresenter {
    private static final String TAG = "LatestNewsPresenter";

    private IRequestLatestModel mDataServer;
    private ILatestNewsView mLatestNewsView;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    public LatestNewsPresenter(ILatestNewsView latestNewsView) {
        mLatestNewsView = latestNewsView;
        mDataServer = new RequestLatestNewsModel(this);
    }

    @Override
    public void handleData(String jsonData) {
        LatestNews latestNews = AppManager.getGson().fromJson(jsonData, LatestNews.class);
        if (latestNews != null) {
            final List<String> titles = new ArrayList<>();
            List<LatestNews.StoriesBean> stories = latestNews.getStories();
            for (LatestNews.StoriesBean story : stories) {
                titles.add(story.getTitle());
            }
            if (titles.size() != 0) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mLatestNewsView.showLatestViewTitle(titles);

                    }
                });
            }
        } else {
            Log.d(TAG, ">> handleData >> " + "latestNews is null");
        }
    }

    @Override
    public void getDataFromServer() {
        mDataServer.requestLatestNews();
    }
}
