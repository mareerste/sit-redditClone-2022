package com.example.redditcloneapp.service.client;

import com.google.gson.annotations.SerializedName;

public class TokenResponse {

    @SerializedName("accessToken")
    private String access_token;
    @SerializedName("expiresIn")
    private String expires_in;
    @SerializedName("username")
    private String username;

    public TokenResponse() {
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccess_token() {
        return access_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "TokenResponse{" +
                "access_token='" + access_token + '\'' +
                ", expires_in='" + expires_in + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
