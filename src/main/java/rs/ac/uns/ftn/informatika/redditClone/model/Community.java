package rs.ac.uns.ftn.informatika.redditClone.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Community {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @Column(name = "description", nullable = true)
    private String description;
    @Column(name = "creationDate")
    private String creationDate;
    @ElementCollection
    private Set<String> rules = new HashSet<>();
    @Column(name = "suspended",nullable = false)
    private Boolean isSuspended;
    @Column(name = "suspendedReason",nullable = true)
    private String suspendedReason;
    @OneToMany(mappedBy = "community",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Flair> flairs = new HashSet<>();
    @OneToMany(mappedBy = "community",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Post> posts = new HashSet<>();
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Moderator> moderators = new HashSet<>();
    @OneToMany(mappedBy = "community",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Banned> bannedList = new HashSet<>();

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

    public Set<Banned> getBannedList() {
        return bannedList;
    }

    public void setBannedList(Set<Banned> bannedList) {
        this.bannedList = bannedList;
    }

    public Community() {
    }

    public Community(String name, String description, Set<String> rules, Set<Flair> flairs, Set<Moderator> moderators) {
        this.name = name;
        this.description = description;
        this.creationDate = LocalDate.now().toString();
        this.rules = rules;
        this.isSuspended = false;
        this.flairs = flairs;
        this.moderators = moderators;
    }

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
                ", flairs=" + flairs +
                ", posts=" + posts +
                ", moderators=" + moderators +
                ", bannedList=" + bannedList +
                '}';
    }
}
