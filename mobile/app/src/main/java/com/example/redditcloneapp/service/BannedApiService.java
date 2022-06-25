package com.example.redditcloneapp.service;

import com.example.redditcloneapp.model.Banned;
import com.example.redditcloneapp.model.Report;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BannedApiService {
    @GET("banned/community/{idCommunity}/user/{idUser}")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
    })
    Call<Boolean> isReported(@Path("idCommunity") Integer id, @Path("idUser") String username);

//    banned/community/2/user/marko123

    @POST("banned")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
    })
    Call<Banned> saveBanned(@Body Banned banned);

    @DELETE("banned/{id}")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
    })
    Call<Void> deleteBanned(@Path("id") Integer id);

    @DELETE("banned/{communityId}/user/{username}")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
    })
    Call<Void> deleteBannedByUser(@Path("communityId") Integer communityId,@Path("username") String username);
}
