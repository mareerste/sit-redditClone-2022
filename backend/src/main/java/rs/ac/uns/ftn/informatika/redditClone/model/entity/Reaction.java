package rs.ac.uns.ftn.informatika.redditClone.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import rs.ac.uns.ftn.informatika.redditClone.model.enumerations.ReactionType;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Reaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "type",nullable = false)
    private ReactionType type;
    @Column(name = "date",nullable = false)
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate timestamp;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id",nullable = true)
    private Post post;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "comment_id",nullable = true)
    private Comment comment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ReactionType getType() {
        return type;
    }

    public void setType(ReactionType type) {
        this.type = type;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
    public Reaction() {
        this.timestamp = LocalDate.now();
    }
    public Reaction(User user, Post post) {
        this.type = ReactionType.UPVOTE;
        this.timestamp = LocalDate.now();
        this.user = user;
        this.post = post;
    }
    public Reaction(User user, Comment comment) {
        this.type = ReactionType.UPVOTE;
        this.timestamp = LocalDate.now();
        this.user = user;
        this.comment = comment;
    }

    public Reaction(ReactionType type, User user, Comment comment) {
        this.type = type;
        this.timestamp = LocalDate.now();
        this.user = user;
        this.comment = comment;
    }

    public Reaction(ReactionType type, User user, Post post) {
        this.type = type;
        this.timestamp = LocalDate.now();
        this.user = user;
        this.post = post;
    }

    @Override
    public String toString() {
        return "Reaction{" +
                "id=" + id +
                ", type=" + type +
                ", timestamp=" + timestamp +
                ", user=" + user +
                ", post=" + post +
                ", comment=" + comment +
                '}';
    }
}
