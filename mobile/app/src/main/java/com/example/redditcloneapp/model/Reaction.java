package com.example.redditcloneapp.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.redditcloneapp.model.enums.ReactionType;

import java.io.Serializable;
import java.time.LocalDate;

public class Reaction implements Serializable {
    private Integer id;
    private ReactionType type;
    private LocalDate timestamp;
    private User user;

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

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public Reaction(Integer id, ReactionType type, User user) {
        this.id = id;
        this.type = type;
        this.timestamp = LocalDate.now();
        this.user = user;
    }
}
