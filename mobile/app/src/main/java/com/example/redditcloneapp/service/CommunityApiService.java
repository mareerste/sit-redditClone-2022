package com.example.redditcloneapp.service;

import com.example.redditcloneapp.model.Community;
import com.example.redditcloneapp.model.Post;
import com.example.redditcloneapp.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CommunityApiService {

    @GET("community/{id}/posts")
    Call<List<Post>> getCommunityPosts(@Path("id") Integer id);
}
