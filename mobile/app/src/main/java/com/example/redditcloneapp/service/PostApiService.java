package com.example.redditcloneapp.service;

import com.example.redditcloneapp.model.Community;
import com.example.redditcloneapp.model.Post;
import com.example.redditcloneapp.model.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PostApiService {
    @GET("post/all")
    Call<List<Post>> getAllPosts();

    @GET("post/{id}/community")
    Call<Community> getCommunityForPost(@Path("id") Integer id);

    @PUT("post")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
    })
    Call<Post> updatePost(@Body Post post);

    @GET("image/get")
    Call<ResponseBody> getImage(@Query("path") String imagePath);

}
