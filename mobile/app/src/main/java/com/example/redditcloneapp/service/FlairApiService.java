package com.example.redditcloneapp.service;

import com.example.redditcloneapp.model.Flair;
import com.example.redditcloneapp.model.Post;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface FlairApiService {

    @POST("flair")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
    })
    Call<Flair> saveFlair(@Body Flair flair);
}
