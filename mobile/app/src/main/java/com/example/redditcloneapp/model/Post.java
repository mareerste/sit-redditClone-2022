package com.example.redditcloneapp.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

public class Post implements Serializable {
    private Integer id;
    private String title;
    private String text;
    private LocalDate creationDate;
    private String imagePath;
    private Boolean isDeleted;
    private User user;
    private Flair flair;
    private Set<Comment> comments;
    private Set<Reaction> reactions;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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

    public Flair getFlair() {
        return flair;
    }

    public void setFlair(Flair flair) {
        this.flair = flair;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<Reaction> getReactions() {
        return reactions;
    }

    public void setReactions(Set<Reaction> reactions) {
        this.reactions = reactions;
    }

    public Post(Integer id, String title, String text, LocalDate creationDate, String imagePath, Boolean isDeleted, User user, Flair flair) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.creationDate = creationDate;
        this.imagePath = imagePath;
        this.isDeleted = isDeleted;
        this.user = user;
        this.flair = flair;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Post(Integer id, String title, String text, User user, Flair flair, Set<Comment> comments, Set<Reaction> reactions) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.creationDate = LocalDate.now();
        this.isDeleted = false;
        this.user = user;
        this.flair = flair;
        this.comments = comments;
        this.reactions = reactions;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", creationDate=" + creationDate +
                ", imagePath='" + imagePath + '\'' +
                ", isDeleted=" + isDeleted +
                ", user=" + user +
                ", flair=" + flair +
                '}';
    }
}
