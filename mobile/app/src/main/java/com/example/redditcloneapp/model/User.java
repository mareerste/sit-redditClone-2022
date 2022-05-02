package com.example.redditcloneapp.model;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModel;

import java.io.Serializable;
import java.time.LocalDate;

public class User implements Serializable {
    protected String username;
    protected String password;
    protected String email;
    protected String avatar;
    protected LocalDate registrationDate;
    protected String description;
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

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
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

    public User(String username, String password, String email, String avatar, LocalDate registrationDate, String description, Boolean deleted) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.avatar = avatar;
        this.registrationDate = registrationDate;
        this.description = description;
        this.deleted = deleted;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public User(String username, String password, String email, String description, Boolean deleted) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.registrationDate = LocalDate.now();
        this.description = description;
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", registrationDate=" + registrationDate +
                ", description='" + description + '\'' +
                ", deleted=" + deleted +
                '}';
    }
}
