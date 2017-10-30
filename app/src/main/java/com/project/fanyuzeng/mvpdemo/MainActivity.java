package com.project.fanyuzeng.mvpdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.project.fanyuzeng.mvpdemo.view.LatestNewsTitleActivity;
import com.project.fanyuzeng.mvpdemo.view.SohuAlbumInfoActivity;

/**
 * @author:fanyuzeng
 * @date: 2017/10/30 13:40
 * @desc:
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnDemo1 = (Button) findViewById(R.id.id_btn_demo1);
        Button btnDemo2 = (Button) findViewById(R.id.id_btn_demo2);
        btnDemo1.setOnClickListener(this);
        btnDemo2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.id_btn_demo1:
                intent = new Intent(this, LatestNewsTitleActivity.class);
                break;
            case R.id.id_btn_demo2:
                intent = new Intent(this, SohuAlbumInfoActivity.class);
                break;
            default:
                break;

        }
        if (intent != null) {
            startActivity(intent);
        } else {
            throw new NullPointerException("intent is null");
        }
    }
}
