package com.example.redditcloneapp.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.redditcloneapp.model.enums.ReactionType;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.Contract;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Post implements Serializable {
    @SerializedName("id")
    private Integer id;
    @SerializedName("title")
    private String title;
    @SerializedName("text")
    private String text;
    @SerializedName("creationDate")
    private String creationDate;
    @SerializedName("imagePath")
    private String imagePath;
    @SerializedName("deleted")
    private Boolean deleted;
    @SerializedName("user")
    private User user;
    @SerializedName("flair")
    private Flair flair;
    @SerializedName("comments")
    private ArrayList<Comment> comments;
    @SerializedName("reactions")
    private Integer reactions;
    private Integer karma = 0;

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

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public User getUser() {
        return user;
    }

    public Integer getKarma() {
        return karma;
    }

    public void setKarma(Integer karma) {
        this.karma = karma;
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

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public Integer getReactions() {
        return reactions;
    }

    public void setReactions(Integer reactions) {
        this.reactions = reactions;
    }

    public Post(Integer id, String title, String text, String creationDate, String imagePath, Boolean isDeleted, User user, Flair flair) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.creationDate = creationDate;
        this.imagePath = imagePath;
        this.deleted = isDeleted;
        this.user = user;
        this.flair = flair;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Post(Integer id, String title, String text, User user, Flair flair, ArrayList<Comment> comments, Integer reactions) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.creationDate = LocalDate.now().toString();
        this.deleted = false;
        this.user = user;
        this.flair = flair;
        this.comments = comments;
        this.reactions = reactions;
    }

    public Post(String title, String text, Flair flair) {
        this.title = title;
        this.text = text;
        this.flair = flair;
    }

    public Post(String title, String text, Flair flair, String imagePath) {
        this.title = title;
        this.text = text;
        this.flair = flair;
        this.imagePath = imagePath;
    }

    //    public String getPostReaction(){
//        int vote = 0;
//        for (Reaction r:this.reactions) {
//            if(r.getType() == ReactionType.UPVOTE)
//                vote++;
//            else
//                vote--;
//        }
//        return String.valueOf(vote);
//    }

    public Post() {
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", creationDate=" + creationDate +
                ", imagePath='" + imagePath + '\'' +
                ", isDeleted=" + deleted +
                ", user=" + user +
                ", flair=" + flair +
                '}';
    }
}
