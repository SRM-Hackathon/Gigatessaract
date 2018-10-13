package com.example.rahul.srmhackandroid.RestAPI;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("exec.php")
    Call<String> postData(@Body ImageInformation imageInformation);

    @POST("getCod")
    Call<String> getData(@Body String values);

}
