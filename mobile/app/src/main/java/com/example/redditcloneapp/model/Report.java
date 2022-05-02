package com.example.redditcloneapp.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.redditcloneapp.model.enums.ReportReason;

import java.io.Serializable;
import java.time.LocalDate;

public class Report implements Serializable {

    private Integer id;
    private ReportReason reason;
    private LocalDate timestamp;
    private User user;
    private Boolean accepted;
    private Post post;
    private Comment comment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ReportReason getReason() {
        return reason;
    }

    public void setReason(ReportReason reason) {
        this.reason = reason;
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

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Report(Integer id, ReportReason reason, User user, Post post, Comment comment) {
        this.id = id;
        this.reason = reason;
        this.timestamp = LocalDate.now();
        this.user = user;
        this.accepted = null;
        this.post = post;
        this.comment = comment;
    }
}
