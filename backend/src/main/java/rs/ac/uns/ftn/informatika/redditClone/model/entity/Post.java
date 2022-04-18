package rs.ac.uns.ftn.informatika.redditClone.model.entity;

import javax.persistence.*;
import java.time.LocalDate;

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
    @Column(name = "deleted",nullable = false)
    private Boolean isDeleted;

//    @OneToMany(mappedBy = "post",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//    private Set<Comment> comments = new HashSet<>();
//    @OneToMany(mappedBy = "post",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//    private Set<Reaction> reactions = new HashSet<>();
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
//    @OneToMany(mappedBy = "post",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//    private Set<Report> reports = new HashSet<>();
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "flair_id",nullable = true)
    private Flair flair;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "community_id")
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

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public Post() {
    }

    public Post(String title, String text, String imagePath, User user, Flair flair, Community community) {
        this.title = title;
        this.text = text;
        this.imagePath = imagePath;
        this.user = user;
        this.isDeleted = false;
        this.flair = flair;
        this.community = community;
    }

    public Post(String title, String text, User user, Flair flair, Community community) {
        this.title = title;
        this.text = text;
        this.isDeleted = false;
        this.user = user;
        this.flair = flair;
        this.community = community;
    }

    public Post(String title, String text, LocalDate creationDate, String imagePath, User user, Flair flair, Community community) {
        this.title = title;
        this.text = text;
        this.creationDate = creationDate;
        this.imagePath = imagePath;
        this.isDeleted = false;
        this.user = user;
        this.flair = flair;
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
                ", user=" + user +
                ", flair=" + flair +
                '}';
    }
}
