package com.example.redditcloneapp.service.client;

import androidx.annotation.NonNull;

public enum Role {
    USER("ROLE_USER"),
    MODERATOR("ROLE_MODERATOR"),
    ADMINISTRATOR("ROLE_ADMINISTRATOR");

    private String roleName;

    private Role(String s) {
        this.roleName = s;
    }

    @NonNull
    @Override
    public String toString() {
        return roleName;
    }
}
