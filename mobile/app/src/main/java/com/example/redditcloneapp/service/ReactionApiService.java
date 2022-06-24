package com.example.redditcloneapp.service;

import com.example.redditcloneapp.model.Comment;
import com.example.redditcloneapp.model.Post;
import com.example.redditcloneapp.model.Reaction;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ReactionApiService {

    @POST("reaction")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
    })
    Call<Reaction> saveReaction(@Body Reaction reaction);

    @GET("reaction/post/{id}/karma")
    Call<Integer> getPostsKarma(@Path("id") Integer id);

    @GET("reaction/comment/{id}/karma")
    Call<Integer> getCommentKarma(@Path("id") Integer id);
}
