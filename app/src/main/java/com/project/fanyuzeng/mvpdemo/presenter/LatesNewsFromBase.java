package com.project.fanyuzeng.mvpdemo.presenter;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;

import com.project.fanyuzeng.mvpdemo.Constants;
import com.project.fanyuzeng.mvpdemo.response.LatestNews;
import com.project.fanyuzeng.mvpdemo.view.ILatestNewsView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanyuzeng on 2017/10/20.
 * Function:
 */  // TODO: 2017/10/20 此处的泛型又是LatestNews
public class LatesNewsFromBase extends BasePresenter<Nullable, LatestNews> {
    private static final String TAG = "LatesNewsFromBase";
    private ILatestNewsView mBaseView;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    public LatesNewsFromBase(ILatestNewsView baseView, Class<LatestNews> clazz) {
        super(baseView, clazz);
        mBaseView = baseView;
        getModel().setRequestUrl(Constants.LATEST_NEWS);

    }

    @Override
    public void serverResponse(LatestNews latestNews) {
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
                        mBaseView.showLatestViewTitle(titles);
                        mBaseView.showProgress(false);

                    }
                });
            }
        } else {
            Log.d(TAG, ">> handleData >> " + "latestNews is null");
        }

    }
}