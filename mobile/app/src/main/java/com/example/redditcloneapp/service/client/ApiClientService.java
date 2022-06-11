package com.example.redditcloneapp.service.client;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiClientService {

    @POST("user/login")
        @Headers({
                "Accept: application/json",
                "Content-Type: application/json",
        })
        Call<TokenResponse> getToken(@Body UserLogin user);
}
