package com.project.fanyuzeng.mvpdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.project.fanyuzeng.mvpdemo.R;

import java.util.List;

/**
 * Created by fanyuzeng on 2017/10/20.
 * Function:
 */
public class LatestNewsAdapter extends BaseAdapter {
    private List<String> mTitleList;
    private Context mContext;
    private LayoutInflater mInflater;

    public LatestNewsAdapter(List<String> titleList, Context context) {
        this.mTitleList = titleList;
        this.mContext = context;
        mInflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return mTitleList.size();
    }

    @Override
    public String getItem(int i) {
        return mTitleList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        String title = getItem(position);
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.latest_news_title_item, null);
            holder.title = convertView.findViewById(R.id.id_tv_title);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.title.setText(title);


        return convertView;
    }

    static class ViewHolder {
        TextView title;
    }
}
