package com.example.redditcloneapp.service;

import com.example.redditcloneapp.model.Community;
import com.example.redditcloneapp.model.Post;
import com.example.redditcloneapp.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CommunityApiService {

    @GET("community/{id}/posts")
    Call<List<Post>> getCommunityPosts(@Path("id") Integer id);

    @POST("community/{id}/posts")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
    })
    Call<Post> savePost(@Path("id") Integer id,@Body Post post);
}
