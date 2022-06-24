package com.example.redditcloneapp.model;

import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModel;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.LocalDate;

public class User implements Serializable {
    @SerializedName("username")
    protected String username;
    @SerializedName("password")
    protected String password;
    @SerializedName("email")
    protected String email;
    @SerializedName("avatar")
    protected String avatar;
    @SerializedName("registrationDate")
    protected String registrationDate;
    @SerializedName("description")
    protected String description;
    @SerializedName("displayName")
    private String displayName;
    @SerializedName("deleted")
    protected Boolean deleted = false;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public User(String username, String password, String email, String avatar, String registrationDate, String description, Boolean deleted) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.avatar = avatar;
        this.registrationDate = registrationDate;
        this.description = description;
        this.deleted = deleted;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public User(String username, String password, String email, String description) {
        this.username = username;
        this.password = password;
        this.email = email;
//        this.registrationDate = LocalDate.now().toString();
        this.description = description;
//        this.deleted = false;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", registrationDate='" + registrationDate + '\'' +
                ", description='" + description + '\'' +
                ", displayName='" + displayName + '\'' +
                ", deleted=" + deleted +
                '}';
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        User user = (User) obj;
        if (user.getUsername().equals(this.username))
            return true;
        else
            return false;
    }

}
