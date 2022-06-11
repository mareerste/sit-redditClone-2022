package com.example.redditcloneapp.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.LocalDate;

public class Banned implements Serializable {
    @SerializedName("id")
    private Integer id;
    @SerializedName("timestamp")
    private LocalDate timestamp;
    @SerializedName("moderator")
    private User moderator;
    @SerializedName("community")
    private Community community;
    @SerializedName("user")
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    public User getModerator() {
        return moderator;
    }

    public void setModerator(Moderator moderator) {
        this.moderator = moderator;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Banned(Integer id, User moderator, Community community, User user) {
        this.id = id;
        this.timestamp = LocalDate.now();
        this.moderator = moderator;
        this.community = community;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Banned{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", moderator=" + moderator +
                ", community=" + community +
                ", user=" + user +
                '}';
    }
}
