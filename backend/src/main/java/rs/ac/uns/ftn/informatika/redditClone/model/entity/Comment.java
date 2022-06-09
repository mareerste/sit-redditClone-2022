package rs.ac.uns.ftn.informatika.redditClone.model.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "text", nullable = false)
    private String text;
    @Column(name = "timestamp", nullable = false)
    private LocalDate timestamp;
    @Column(name = "deleted", nullable = false)
    private Boolean isDeleted;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
//    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//    @JoinColumn(name = "parentCommentId", referencedColumnName = "id",nullable = true)
//    private Comment parentComment;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "childComments", joinColumns = @JoinColumn(name = "comment_id",referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name = "child_id", referencedColumnName = "id"))
    private Set<Comment> childComments = new HashSet<>();
//    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//    @JoinColumn(name = "post_id",nullable = true)
//    private Post post;
    @OneToMany(mappedBy = "comment",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    Set<Reaction> reactions = new HashSet<>();
//    @OneToMany(mappedBy = "comment",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//    Set<Report> reports = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Set<Comment> getChildComments() {
        return childComments;
    }

    public void setChildComments(Set<Comment> childComments) {
        this.childComments = childComments;
    }

    public Comment() {
        this.timestamp = LocalDate.now();
        this.isDeleted = false;
    }
    //create
    public Comment(String text) {
        this.text = text;
        this.timestamp = LocalDate.now();
        this.isDeleted = false;
    }

    public Comment(String text, User user,Set<Comment> childComments) {
        this.text = text;
        this.user = user;
        this.childComments = childComments;
        this.timestamp = LocalDate.now();
        this.isDeleted = false;
    }
    public Comment(String text, User user) {
        this.text = text;
        this.user = user;
//        this.post = post;
        this.timestamp = LocalDate.now();
        this.isDeleted = false;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", timestamp=" + timestamp +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
