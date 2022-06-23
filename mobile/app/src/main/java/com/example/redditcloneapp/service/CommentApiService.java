package com.example.redditcloneapp.service;

import com.example.redditcloneapp.model.Comment;
import com.example.redditcloneapp.model.Post;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CommentApiService {

    @POST("comment")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
    })
    Call<Comment> saveComment(@Body Comment comment);

    @PUT("comment")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
    })
    Call<Comment> updateComment(@Body Comment comment);
}
