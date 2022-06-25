package com.example.redditcloneapp.service;

import com.example.redditcloneapp.model.Reaction;
import com.example.redditcloneapp.model.Report;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ReportApiService {
    @POST("report")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
    })
    Call<Report> saveReport(@Body Report report);
}
