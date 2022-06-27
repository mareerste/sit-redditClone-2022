package com.example.redditcloneapp.service;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ImageApiService {

//    @Multipart
//    @POST("image")
//    Call<ResponseBody> saveImage(@Part MultipartBody.Part part);

    @Multipart
    @POST("image")
    Call<Map<String,String>> saveImage(@Part MultipartBody.Part part);
}
