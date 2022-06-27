package com.example.redditcloneapp.service;

import com.example.redditcloneapp.model.Comment;
import com.example.redditcloneapp.model.Community;
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
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserApiService {
    @GET("user/{username}")
    Call<User> getUser(@Path("username") String username);

    @GET("user/{username}/karma")
    Call<Integer> getUsersKarma(@Path("username") String username);

    @GET("user/{username}/posts")
    Call<List<Post>> getUserPosts(@Path("username") String username);

    @GET("user/{username}/comments")
    Call<List<Comment>> getUserComments(@Path("username") String username);

    @GET("user/{username}/communities")
    Call<List<Community>> getUserCommunities(@Path("username") String username);

    @GET("user")
    Call<List<User>> getUsers();

    @POST("user")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
    })
    Call<User> saveUser(@Body User user);

    @PUT("user")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
    })
    Call<User> updateUser(@Body User user);
}
