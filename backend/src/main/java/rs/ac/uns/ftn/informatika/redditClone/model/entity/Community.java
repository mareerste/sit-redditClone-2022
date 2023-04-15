package rs.ac.uns.ftn.informatika.redditClone.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
public class Community {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @Column(name = "description", nullable = true)
    private String description;
    @Column(name = "creationDate")
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate creationDate;
    @ElementCollection
    private Set<String> rules = new HashSet<>();
    @Column(name = "suspended",nullable = false)
    private Boolean isSuspended;
    @Column(name = "suspendedReason",nullable = true)
    private String suspendedReason;
//    @OneToMany(mappedBy = "community",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @ManyToMany
    @JoinTable(name = "communityFlairs", joinColumns = @JoinColumn(name = "community_id",referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name = "flair_id", referencedColumnName = "id"))
    private Set<Flair> flairs = new HashSet<>();
//    @JoinTable(name = "communityPosts", joinColumns = @JoinColumn(name = "community_id",referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id"))
//    @OneToMany(mappedBy = "community",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "communityPosts", joinColumns = @JoinColumn(name = "community_id",referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id"))
    private Set<Post> posts = new HashSet<>();
    @ManyToMany
    @JoinTable(name = "moderators", joinColumns = @JoinColumn(name = "community_id",referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name = "moderator_id", referencedColumnName = "username"))
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

    public Set<User> getModerators() {
        return moderators;
    }

    public void setModerators(Set<User> moderators) {
        this.moderators = moderators;
    }

    public Community() {
        this.creationDate = LocalDate.now();
        this.isSuspended = false;
    }

    public Community(String name, String description, Set<String> rules, Set<Flair> flairs, Set<User> moderators) {
        this.name = name;
        this.description = description;
        this.creationDate = LocalDate.now();
        this.rules = rules;
        this.isSuspended = false;
        this.flairs = flairs;
        this.moderators = moderators;
    }

    public Community(String name, String description, Set<String> rules) {
        this.name = name;
        this.description = description;
        this.creationDate = LocalDate.now();
        this.rules = rules;
        this.isSuspended = false;
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
                '}';
    }
}
