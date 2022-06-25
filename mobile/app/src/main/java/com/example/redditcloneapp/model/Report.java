package com.example.redditcloneapp.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.redditcloneapp.model.enums.ReactionType;
import com.example.redditcloneapp.model.enums.ReportReason;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.LocalDate;

public class Report implements Serializable {
    @SerializedName("id")
    private Integer id;
    @SerializedName("reason")
    private ReportReason reason;
    @SerializedName("timestamp")
    private String timestamp;
    @SerializedName("user")
    private User user;
    @SerializedName("accepted")
    private Boolean accepted;
    @SerializedName("post")
    private Post post;
    @SerializedName("comment")
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
    public Report(ReportReason reason, Post post) {
        this.reason = reason;
        this.timestamp = null;
        this.accepted = null;
        this.comment = null;
        this.post = post;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Report(ReportReason reason, Comment comment) {
        this.reason = reason;
        this.timestamp = null;
        this.accepted = null;
        this.comment = comment;
        this.post = null;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Report(Integer id, ReportReason reason, User user, Post post, Comment comment) {
        this.id = id;
        this.reason = reason;
        this.timestamp = LocalDate.now().toString();
        this.user = user;
        this.accepted = null;
        this.post = post;
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", reason=" + reason +
                ", timestamp=" + timestamp +
                ", user=" + user.getUsername() +
                ", accepted=" + accepted +
                ", post=" + ((post == null) ? " N/A" : (post.getId() + " " + post.getText())) +
                ", comment=" + ((comment == null) ? " N/A" : comment.getText()) +
                '}';
    }
}
