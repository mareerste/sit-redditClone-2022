package rs.ac.uns.ftn.informatika.redditClone.model;

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
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parentColumnId", nullable = true)
    private Comment parentComment;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private Post post;
    @OneToMany(mappedBy = "comment",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    Set<Reaction> reactions = new HashSet<>();
    @OneToMany(mappedBy = "comment",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    Set<Report> reports = new HashSet<>();

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

    public Comment() {
    }
    //create
    public Comment(String text) {
        this.text = text;
        this.timestamp = LocalDate.now();
        this.isDeleted = false;
    }

    public Comment(String text, User user, Comment parentComment, Post post) {
        this.text = text;
        this.user = user;
        this.parentComment = parentComment;
        this.post = post;
        this.timestamp = LocalDate.now();
        this.isDeleted = false;
    }
    public Comment(String text, User user, Post post) {
        this.text = text;
        this.user = user;
        this.post = post;
        this.timestamp = LocalDate.now();
        this.isDeleted = false;
    }
    public Comment(String text, Comment comment, Post post) {
        this.text = text;
        this.parentComment =  comment;
        this.post = post;
        this.timestamp = LocalDate.now();
        this.isDeleted = false;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Set<Reaction> getReactions() {
        return reactions;
    }

    public void setReactions(Set<Reaction> reactions) {
        this.reactions = reactions;
    }

    public Set<Report> getReports() {
        return reports;
    }

    public void setReports(Set<Report> reports) {
        this.reports = reports;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
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
