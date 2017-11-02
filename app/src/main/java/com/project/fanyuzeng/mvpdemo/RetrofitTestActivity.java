package com.project.fanyuzeng.mvpdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.project.fanyuzeng.mvpdemo.api.GetSohuSerialsRequest;
import com.project.fanyuzeng.mvpdemo.api.PostYoudaoTransRequest;
import com.project.fanyuzeng.mvpdemo.response.Album;
import com.project.fanyuzeng.mvpdemo.response.Translation1;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author:fanyuzeng
 * @date: 2017/11/1 15:04
 * @desc: {@link Retrofit} 的测试类
 */
public class RetrofitTestActivity extends AppCompatActivity {
    private static final String TAG = "RetrofitTestActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_test);

        requestSohuSerialsData();

//        postRequest();
    }

    private void postRequest() {
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fanyi.youdao.com/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 步骤5:创建 网络请求接口 的实例
        PostYoudaoTransRequest request = retrofit.create(PostYoudaoTransRequest.class);

        //对 发送请求 进行封装(设置需要翻译的内容)
        Call<Translation1> call = request.getCall("I love you");

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<Translation1>() {

            //请求成功时回调
            @Override
            public void onResponse(Call<Translation1> call, Response<Translation1> response) {
                // 步骤7：处理返回的数据结果：输出翻译的内容
                System.out.println(response.body().getTranslateResult().get(0).get(0).getTgt());
                Log.d(TAG, ">> onResponse >> " + response.body().getTranslateResult().get(0).get(0).getTgt());
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<Translation1> call, Throwable throwable) {
                System.out.println("请求失败");
                System.out.println(throwable.getMessage());

                Log.d(TAG, ">> onFailure >> " + "请求失败：" + throwable.getMessage());
            }
        });
    }


    private void requestSohuSerialsData() {
        //创建 Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.RETROFIT_SOHU_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //创建 网络请求 接口实例
        GetSohuSerialsRequest sohuSerialsRequest = retrofit.create(GetSohuSerialsRequest.class);
        //对 发送请求 进行封装
        Call<Album> albumCall = sohuSerialsRequest.getSohuSerialsData(prepareSohuSerialsParams());
        //发送 异步的 网络请求
        albumCall.enqueue(new Callback<Album>() {
            @Override
            public void onResponse(Call<Album> call, Response<Album> response) {
                //处理返回的数据
                Log.d(TAG, ">> onResponse >> " + response.body().toString());
            }

            @Override
            public void onFailure(Call<Album> call, Throwable t) {
                //处理请求失败的情况
                Log.d(TAG, ">> onFailure >> " + t.toString());
            }
        });


    }

    private Map<String, String> prepareSohuSerialsParams() {
        HashMap<String, String> paramsMap = new HashMap<>(12);
        paramsMap.put("cid", "2");
        paramsMap.put("o", "1");
        paramsMap.put("plat", "6");
        paramsMap.put("poid", "1");
        paramsMap.put("api_key", "9854b2afa779e1a6bff1962447a09dbd");
        paramsMap.put("sver", "6.2.0");
        paramsMap.put("sysver", "4.4.2");
        paramsMap.put("partner", "47");
        paramsMap.put("page", "1");
        paramsMap.put("page_size", "3");
        return paramsMap;
    }
}
