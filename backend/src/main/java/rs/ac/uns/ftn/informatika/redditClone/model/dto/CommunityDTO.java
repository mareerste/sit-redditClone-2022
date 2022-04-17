package rs.ac.uns.ftn.informatika.redditClone.model.dto;

import rs.ac.uns.ftn.informatika.redditClone.model.entity.Comment;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Community;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class CommunityDTO implements Serializable {
    private Integer id;
    private String name;
    private String description;
    private String creationDate;
    private Set<String> rules = new HashSet<>();
    private Boolean isSuspended;
    private String suspendedReason;

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
    public CommunityDTO() {
    }

    public CommunityDTO(Integer id, String name, String description, String creationDate, Set<String> rules, Boolean isSuspended, String suspendedReason) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        this.rules = rules;
        this.isSuspended = isSuspended;
        this.suspendedReason = suspendedReason;
    }
    public CommunityDTO (Community community){this(community.getId(), community.getName(), community.getDescription(), community.getCreationDate(), community.getRules(),community.getSuspended(), community.getSuspendedReason());}
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
