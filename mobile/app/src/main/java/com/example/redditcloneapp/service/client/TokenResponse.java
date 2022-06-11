package com.example.redditcloneapp.service.client;

import com.google.gson.annotations.SerializedName;

public class TokenResponse {

    @SerializedName("accessToken")
    private String access_token;
    @SerializedName("expiresIn")
    private String expires_in;
    @SerializedName("username")
    private String username;
    @SerializedName("role")
    private String role;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public TokenResponse(String access_token, String expires_in, String username, String role) {
        this.access_token = access_token;
        this.expires_in = expires_in;
        this.username = username;
        this.role = role;
    }

    @Override
    public String toString() {
        return "TokenResponse{" +
                "access_token='" + access_token + '\'' +
                ", expires_in='" + expires_in + '\'' +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
