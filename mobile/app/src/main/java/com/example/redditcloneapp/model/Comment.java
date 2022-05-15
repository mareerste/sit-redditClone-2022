package com.example.redditcloneapp.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.redditcloneapp.model.enums.ReactionType;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Comment implements Serializable {
    private Integer id;
    private String text;
    private LocalDate timestamp;
    private Boolean isDeleted;
    private User user;
    private List<Comment> childComments = new ArrayList<>();
    private List<Reaction> reactions = new ArrayList<>();

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

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
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

    public List<Comment> getChildComments() {
        return childComments;
    }

    public List<Reaction> getReactions() {
        return reactions;
    }

    public void setReactions(List<Reaction> reactions) {
        this.reactions = reactions;
    }

    public void setChildComments(List<Comment> childComments) {
        this.childComments = childComments;
    }

    public Comment(Integer id, String text, LocalDate timestamp, Boolean isDeleted, User user, List<Comment> childComments, List<Reaction> reactions) {
        this.id = id;
        this.text = text;
        this.timestamp = timestamp;
        this.isDeleted = isDeleted;
        this.user = user;
        this.childComments = childComments;
        this.reactions = reactions;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Comment(Integer id, String text, User user, List<Comment> childComments, List<Reaction> reactions) {
        this.id = id;
        this.text = text;
        this.timestamp = LocalDate.now();
        this.isDeleted = false;
        this.user = user;
        this.childComments = childComments;
        this.reactions = reactions;
    }

    public String getCommentReaction(){
        int vote = 0;
        for (Reaction r:this.reactions) {
            if(r.getType() == ReactionType.UPVOTE)
                vote++;
            else
                vote--;
        }
        return String.valueOf(vote);
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
