package rs.ac.uns.ftn.informatika.redditClone.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Comment;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Community;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Moderator;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.User;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class CommunityDTO implements Serializable {
    private Integer id;
    private String name;
    private String description;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate creationDate;
    private Set<String> rules = new HashSet<>();
    private Boolean isSuspended;
    private String suspendedReason;
    private Set<User> moderators = new HashSet<>();

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
    public Set<User> getModerators() {
        return moderators;
    }
    public void setModerators(Set<User> moderators) {
        this.moderators = moderators;
    }

    public CommunityDTO() {
    }

    public CommunityDTO(Integer id, String name, String description, LocalDate creationDate, Set<String> rules, Boolean isSuspended, String suspendedReason, Set<User> moderators) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        this.rules = rules;
        this.isSuspended = isSuspended;
        this.suspendedReason = suspendedReason;
        this.moderators = moderators;
    }
    public CommunityDTO (Community community){this(community.getId(), community.getName(), community.getDescription(), community.getCreationDate(), community.getRules(),community.getSuspended(), community.getSuspendedReason(), community.getModerators());}
    @Override
    public String toString() {
        return "Community{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", rules=" + rules +
                ", isSuspended=" + isSuspended +
                ", suspendedReason='" + suspendedReason + '\'' +
                '}';
    }
}
