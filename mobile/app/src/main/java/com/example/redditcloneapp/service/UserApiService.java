package com.example.redditcloneapp.service;

import com.example.redditcloneapp.model.Post;
import com.example.redditcloneapp.model.User;
import com.example.redditcloneapp.service.client.TokenResponse;
import com.example.redditcloneapp.service.client.UserLogin;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserApiService {
    @GET("user/{username}")
    Call<User> getUser(@Path("username") String username);

    @POST("user")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
    })
    Call<User> saveUser(@Body User user);
}
