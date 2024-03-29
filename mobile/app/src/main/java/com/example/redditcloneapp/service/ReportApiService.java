package com.example.redditcloneapp.service;

import com.example.redditcloneapp.model.Reaction;
import com.example.redditcloneapp.model.Report;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ReportApiService {
    @POST("report")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
    })
    Call<Report> saveReport(@Body Report report);

    @PUT("report/accept")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
    })
    Call<Report> acceptReport(@Body Report report);

    @PUT("report/decline")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
    })
    Call<Report> declineReport(@Body Report report);

    @GET("report/community/{id}/posts")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
    })
    Call<List<Report>> getCommunityReportedPosts(@Path("id") Integer id);

    @GET("report/comments")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
    })
    Call<List<Report>> getCommunityReportedComments();
}
