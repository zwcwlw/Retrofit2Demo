package com.zwcwlw.retrofit2demo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "--";
    private Context context = this;
    private TextView tv_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initRetrofit();
    }

    private void initRetrofit() {
        tv_info= (TextView) findViewById(R.id.tv_info);
    }

    private void initView() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.URL_BASE)
                .build();
        MyServiceInterface serviceInterface = retrofit.create(MyServiceInterface.class);
        Call<ResponseBody> call = serviceInterface.getLatextJsonString();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.e(TAG,"ThreadID:"+Thread.currentThread().getId());

            if (response.isSuccessful()){
                try {
                    String result=response.body().string();
                    //判断当前线程为主线程就更新UI
                    if (Thread.currentThread().getId()==1){
                        tv_info.setText(result);//
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }
}
