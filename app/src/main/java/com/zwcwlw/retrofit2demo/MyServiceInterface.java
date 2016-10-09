package com.zwcwlw.retrofit2demo;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2016/10/9.
 */
public interface MyServiceInterface {
    @GET("article/list/latest?page=1")
    Call<ResponseBody> getLatextJsonString();
}
