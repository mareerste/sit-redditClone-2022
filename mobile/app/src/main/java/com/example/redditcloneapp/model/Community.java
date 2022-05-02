package com.example.redditcloneapp.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Community implements Serializable {
    private Integer id;
    private String name;
    private String description;
    private LocalDate creationDate;
    private Set<String> rules = new HashSet<>();
    private Boolean isSuspended;
    private String suspendedReason;
    private Set<Flair> flairs = new HashSet<>();
    private Set<Post> posts = new HashSet<>();
    private Set<Moderator> moderators = new HashSet<>();

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

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Set<String> getRules() {
        return rules;
    }

    public void setRules(Set<String> rules) {
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

    public Set<Flair> getFlairs() {
        return flairs;
    }

    public void setFlairs(Set<Flair> flairs) {
        this.flairs = flairs;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public Set<Moderator> getModerators() {
        return moderators;
    }

    public void setModerators(Set<Moderator> moderators) {
        this.moderators = moderators;
    }

    public Community(Integer id, String name, String description, LocalDate creationDate, Set<String> rules, Boolean isSuspended, String suspendedReason, Set<Flair> flairs, Set<Post> posts, Set<Moderator> moderators) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        this.rules = rules;
        this.isSuspended = isSuspended;
        this.suspendedReason = suspendedReason;
        this.flairs = flairs;
        this.posts = posts;
        this.moderators = moderators;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Community(Integer id, String name, String description, Set<String> rules, List<Flair> flairs, List<Post> posts, List<Moderator> moderators) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creationDate = LocalDate.now();
        this.rules = rules;
        this.isSuspended = false;
        this.suspendedReason = null;
        this.flairs = (Set<Flair>) flairs;
        this.posts = (Set<Post>) posts;
        this.moderators = (Set<Moderator>) moderators;
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
                ", posts=" + posts +
                ", moderators=" + moderators +
                '}';
    }
}
