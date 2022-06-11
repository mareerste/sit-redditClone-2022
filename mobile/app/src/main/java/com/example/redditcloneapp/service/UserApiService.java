package com.example.redditcloneapp.service;

import com.example.redditcloneapp.model.Post;
import com.example.redditcloneapp.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserApiService {
    @GET("user/{username}")
    Call<User> getUser(@Path("username") String username);
}
