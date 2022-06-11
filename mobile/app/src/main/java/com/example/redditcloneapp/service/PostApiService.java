package com.example.redditcloneapp.service;

import com.example.redditcloneapp.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostApiService {
    @GET("post/all")
    Call<List<Post>> getAllPosts();

}
