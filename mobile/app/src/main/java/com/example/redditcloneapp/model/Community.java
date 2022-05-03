package com.example.redditcloneapp.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Community implements Serializable {
    private Integer id;
    private String name;
    private String description;
    private LocalDate creationDate;
    private ArrayList<String> rules = new ArrayList<>();
    private Boolean isSuspended;
    private String suspendedReason;
    private ArrayList<Flair> flairs = new ArrayList<>();
    private ArrayList<Moderator> moderators = new ArrayList<>();

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

    public ArrayList<Moderator> getModerators() {
        return moderators;
    }

    public void setModerators(ArrayList<Moderator> moderators) {
        this.moderators = moderators;
    }

    public Community(Integer id, String name, String description, LocalDate creationDate, ArrayList<String> rules, Boolean isSuspended, String suspendedReason, ArrayList<Flair> flairs, ArrayList<Moderator> moderators) {
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
    public Community(Integer id, String name, String description, ArrayList<String> rules, ArrayList<Flair> flairs, ArrayList<Moderator> moderators) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creationDate = LocalDate.now();
        this.rules = rules;
        this.isSuspended = false;
        this.suspendedReason = null;
        this.flairs = flairs;
        this.moderators = moderators;
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
