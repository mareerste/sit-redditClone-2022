package rs.ac.uns.ftn.informatika.redditClone.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;
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
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate creationDate;
    @Column(name = "image",nullable = true)
    private String imagePath;
    @Column(name = "deleted",nullable = false)
    private Boolean isDeleted;

//    @OneToMany(mappedBy = "post",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//    private Set<Comment> comments = new HashSet<>();
    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    private Set<Reaction> reactions = new HashSet<>();
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = true)
    private User user;
//    @OneToMany(mappedBy = "post",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//    private Set<Report> reports = new HashSet<>();
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "flair_id",nullable = true)
    private Flair flair;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "postComments", joinColumns = @JoinColumn(name = "post_id",referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name = "comment_id", referencedColumnName = "id"))
    private Set<Comment> comments = new HashSet<>();

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

    public Post() {
        this.creationDate = LocalDate.now();
        this.isDeleted = false;
    }

    public Post(String title, String text, String imagePath, User user, Flair flair, Set<Comment> comments) {
        this.title = title;
        this.text = text;
        this.imagePath = imagePath;
        this.user = user;
        this.isDeleted = false;
        this.flair = flair;
        this.comments = comments;
    }

    public Post(String title, String text, User user, Flair flair) {
        this.title = title;
        this.text = text;
        this.isDeleted = false;
        this.user = user;
        this.flair = flair;
    }

    public Post(String title, String text, LocalDate creationDate, String imagePath, User user, Flair flair, Set<Comment> comments) {
        this.title = title;
        this.text = text;
        this.creationDate = creationDate;
        this.imagePath = imagePath;
        this.isDeleted = false;
        this.user = user;
        this.flair = flair;
        this.comments = comments;
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
                ",comments=" + comments +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        Post post = (Post) obj;
        return this.id == post.getId();
    }
}
