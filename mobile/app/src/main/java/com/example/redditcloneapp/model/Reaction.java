package com.example.redditcloneapp.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.redditcloneapp.model.enums.ReactionType;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.LocalDate;

public class Reaction implements Serializable {
    @SerializedName("id")
    private Integer id;
    @SerializedName("type")
    private ReactionType type;
    @SerializedName("timestamp")
    private String timestamp;
    @SerializedName("user")
    private User user;
    @SerializedName("post")
    private Integer post;
    @SerializedName("comment")
    private Integer comment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ReactionType getType() {
        return type;
    }

    public void setType(ReactionType type) {
        this.type = type;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Reaction(ReactionType type, Integer post, Integer comment) {
        this.type = type;
        this.post = post;
        this.comment = comment;
    }

//    public Reaction(ReactionType type, Integer comment) {
//        this.type = type;
//        this.comment = comment;
//    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Reaction(Integer id, ReactionType type, User user) {
        this.id = id;
        this.type = type;
        this.timestamp = LocalDate.now().toString();
        this.user = user;
    }

    @Override
    public String toString() {
        return "Reaction{" +
                "id=" + id +
                ", type=" + type +
                ", user=" + user +
                ", post=" + post +
                ", comment=" + comment +
                '}';
    }
}
