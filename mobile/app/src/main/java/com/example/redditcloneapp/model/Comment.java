package com.example.redditcloneapp.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.redditcloneapp.model.enums.ReactionType;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Comment implements Serializable {
    @SerializedName("id")
    private Integer id;
    @SerializedName("text")
    private String text;
    @SerializedName("timestamp")
    private String timestamp;
    @SerializedName("deleted")
    private Boolean isDeleted;
    @SerializedName("user")
    private User user;
    @SerializedName("childComments")
    private ArrayList<Comment> childComments = new ArrayList<>();
    @SerializedName("reactions")
    private Integer reactions = 0;

    public Comment(String text) {
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<Comment> getChildComments() {
        return childComments;
    }

    public Integer getReactions() {
        return reactions;
    }

    public void setReactions(Integer reactions) {
        this.reactions = reactions;
    }

    public void setChildComments(ArrayList<Comment> childComments) {
        this.childComments = childComments;
    }

    public Comment(Integer id, String text, String timestamp, Boolean isDeleted, User user, ArrayList<Comment> childComments, Integer reactions) {
        this.id = id;
        this.text = text;
        this.timestamp = timestamp;
        this.isDeleted = isDeleted;
        this.user = user;
        this.childComments = childComments;
        this.reactions = reactions;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Comment(Integer id, String text, User user, ArrayList<Comment> childComments, Integer reactions) {
        this.id = id;
        this.text = text;
        this.timestamp = LocalDate.now().toString();
        this.isDeleted = false;
        this.user = user;
        this.childComments = childComments;
        this.reactions = reactions;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", timestamp=" + timestamp +
                ", isDeleted=" + isDeleted +
                ", user=" + user +
                ", childComments=" + childComments +
                '}';
    }
}
