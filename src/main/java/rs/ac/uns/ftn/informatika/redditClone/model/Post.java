package rs.ac.uns.ftn.informatika.redditClone.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "title",nullable = false)
    private String title;
    @Column(name = "text",nullable = false)
    private String text;
    @Column(name = "date",nullable = false)
    private LocalDate creationDate;
    @Column(name = "image",nullable = true)
    private String imagePath;
    @OneToMany(mappedBy = "post",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    Set<Comment> comments = new HashSet<>();
    @OneToMany(mappedBy = "post",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    Set<Reaction> reactions = new HashSet<>();
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "post",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    Set<Report> reports = new HashSet<>();
    @OneToMany(mappedBy = "post",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    Set<Flair> flairs = new HashSet<>();
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "community_id", nullable = false)
    private Community community;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Report> getReports() {
        return reports;
    }

    public void setReports(Set<Report> reports) {
        this.reports = reports;
    }

    public Set<Flair> getFlairs() {
        return flairs;
    }

    public void setFlairs(Set<Flair> flairs) {
        this.flairs = flairs;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public Post(String title, String text, String imagePath, Set<Reaction> reactions, User user, Set<Flair> flairs, Community community) {
        this.title = title;
        this.text = text;
        this.imagePath = imagePath;
        this.reactions = reactions;
        this.user = user;
        this.flairs = flairs;
        this.community = community;
    }

    public Post(String title, String text, Set<Reaction> reactions, User user, Set<Flair> flairs, Community community) {
        this.title = title;
        this.text = text;
        this.reactions = (Set<Reaction>) new Reaction(user,this);
        this.user = user;
        this.flairs = flairs;
        this.community = community;
    }

    public Post(String title, String text, LocalDate creationDate, String imagePath, Set<Comment> comments, Set<Reaction> reactions, User user, Set<Report> reports, Set<Flair> flairs, Community community) {
        this.title = title;
        this.text = text;
        this.creationDate = creationDate;
        this.imagePath = imagePath;
        this.comments = comments;
        this.reactions = reactions;
        this.user = user;
        this.reports = reports;
        this.flairs = flairs;
        this.community = community;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", creationDate=" + creationDate +
                ", imagePath='" + imagePath + '\'' +
                ", comments=" + comments +
                ", reactions=" + reactions +
                ", user=" + user +
                ", reports=" + reports +
                ", flairs=" + flairs +
                ", community=" + community +
                '}';
    }
}
