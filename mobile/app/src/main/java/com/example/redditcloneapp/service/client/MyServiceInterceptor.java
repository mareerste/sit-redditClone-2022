package com.example.redditcloneapp.service.client;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.annotation.NonNull;

import com.example.redditcloneapp.ui.access.SignInActivity;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

@Singleton
public class MyServiceInterceptor implements Interceptor {
    private String tokenValue;

    public MyServiceInterceptor(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {

        Request newRequest=chain.request().newBuilder()
                .header("Authorization","Bearer "+ tokenValue)
                .build();

        return chain.proceed(newRequest);
    }
}
