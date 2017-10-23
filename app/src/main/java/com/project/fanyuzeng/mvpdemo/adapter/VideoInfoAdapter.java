package com.project.fanyuzeng.mvpdemo.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.fanyuzeng.mvpdemo.R;
import com.project.fanyuzeng.mvpdemo.response.VideoInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanyuzeng on 2017/10/23.
 * Function:
 */
public class VideoInfoAdapter extends RecyclerView.Adapter<VideoInfoAdapter.ViewHolder> {
    private Context mContext;
    private List<VideoInfo> mAlbumList = new ArrayList<>();

    public VideoInfoAdapter(Context context) {
        mContext = context;
    }

    public VideoInfoAdapter(Context context, List<VideoInfo> albumList) {
        mContext = context;
        mAlbumList = albumList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = ((Activity) mContext).getLayoutInflater().inflate(R.layout.album_main_info_item, null);
        ViewHolder holder = new ViewHolder(view);
        view.setTag(holder);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        VideoInfo videoInfo = mAlbumList.get(position);
        holder.mTvAlbumName.setText("片名："+videoInfo.getAlbum_name());
        holder.mTvDirector.setText("导演："+videoInfo.getDirector());
        holder.mTvMainActor.setText("主演："+videoInfo.getMain_actor());
        holder.mTvPublishTime.setText("更新时间："+videoInfo.getPublish_time());
        holder.mTvTotalCount.setText("集数："+videoInfo.getTotal_video_count());
    }

    @Override
    public int getItemCount() {
        return mAlbumList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTvMainActor;
        TextView mTvDirector;
        TextView mTvTotalCount;
        TextView mTvPublishTime;
        TextView mTvAlbumName;

        public ViewHolder(View itemView) {
            super(itemView);

            mTvAlbumName = (TextView) itemView.findViewById(R.id.id_tv_album_name);
            mTvMainActor = (TextView) itemView.findViewById(R.id.id_tv_main_actor);
            mTvDirector = (TextView) itemView.findViewById(R.id.id_tv_director);
            mTvTotalCount = (TextView) itemView.findViewById(R.id.id_tv_total_count);
            mTvPublishTime = (TextView) itemView.findViewById(R.id.id_tv_publish_time);
        }
    }

    public void addData(VideoInfo videoInfo) {
        mAlbumList.add(videoInfo);
    }

    public void cleanData() {
        mAlbumList.clear();
    }
}
