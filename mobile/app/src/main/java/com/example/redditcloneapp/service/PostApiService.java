package com.example.redditcloneapp.service;

import com.example.redditcloneapp.model.Community;
import com.example.redditcloneapp.model.Post;
import com.example.redditcloneapp.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PostApiService {
    @GET("post/all")
    Call<List<Post>> getAllPosts();

    @GET("post/{id}/community")
    Call<Community> getCommunityForPost(@Path("id") Integer id);

}