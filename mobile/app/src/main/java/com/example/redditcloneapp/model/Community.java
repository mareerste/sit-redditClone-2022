package com.example.redditcloneapp.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.lang.reflect.Member;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Community implements Serializable {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("creationDate")
    private String creationDate;
    @SerializedName("rules")
    private ArrayList<String> rules = new ArrayList<>();
    @SerializedName("suspended")
    private Boolean isSuspended;
    @SerializedName("suspendedReason")
    private String suspendedReason;
    @SerializedName("flairs")
    private ArrayList<Flair> flairs = new ArrayList<>();
    @SerializedName("moderators")
    private ArrayList<User> moderators = new ArrayList<>();
    @SerializedName("posts")
    private ArrayList<Post> posts = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public ArrayList<String> getRules() {
        return rules;
    }

    public void setRules(ArrayList<String> rules) {
        this.rules = rules;
    }

    public Boolean getSuspended() {
        return isSuspended;
    }

    public void setSuspended(Boolean suspended) {
        isSuspended = suspended;
    }

    public String getSuspendedReason() {
        return suspendedReason;
    }

    public void setSuspendedReason(String suspendedReason) {
        this.suspendedReason = suspendedReason;
    }

    public ArrayList<Flair> getFlairs() {
        return flairs;
    }

    public void setFlairs(ArrayList<Flair> flairs) {
        this.flairs = flairs;
    }

    public ArrayList<User> getModerators() {
        return moderators;
    }

    public void setModerators(ArrayList<User> moderators) {
        this.moderators = moderators;
    }

    public void setId(int id) {
        this.id = id;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Community() {
        this.creationDate = null;
        this.isSuspended = false;
        this.suspendedReason = null;
        this.posts = new ArrayList<>();
    }

    public Community(Integer id, String name, String description, String creationDate, ArrayList<String> rules, Boolean isSuspended, String suspendedReason, ArrayList<Flair> flairs, ArrayList<User> moderators) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        this.rules = rules;
        this.isSuspended = isSuspended;
        this.suspendedReason = suspendedReason;
        this.flairs = flairs;
        this.moderators = moderators;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Community(Integer id, String name, String description, ArrayList<String> rules, ArrayList<Flair> flairs, ArrayList<User> moderators, ArrayList<Post> posts) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creationDate = LocalDate.now().toString();
        this.rules = rules;
        this.isSuspended = false;
        this.suspendedReason = null;
        this.flairs = flairs;
        this.moderators = moderators;
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "Community{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", creationDate=" + creationDate +
                ", rules=" + rules +
                ", isSuspended=" + isSuspended +
                ", suspendedReason='" + suspendedReason + '\'' +
                ", flairs=" + flairs +
                ", moderators=" + moderators +
                '}';
    }
}
