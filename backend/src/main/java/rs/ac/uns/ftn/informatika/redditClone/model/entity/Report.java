package rs.ac.uns.ftn.informatika.redditClone.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import rs.ac.uns.ftn.informatika.redditClone.model.enumerations.ReactionType;
import rs.ac.uns.ftn.informatika.redditClone.model.enumerations.ReportReason;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "reason",nullable = false)
    private ReportReason reason;
    @Column(name = "date",nullable = false)
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate timestamp;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;
    @Column(name = "accepted", nullable = true)
    private Boolean accepted;
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

    public ReportReason getReason() {
        return reason;
    }

    public void setReason(ReportReason reason) {
        this.reason = reason;
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

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
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

    public Report() {
        this.timestamp = LocalDate.now();
        this.accepted = null;
    }

    public Report(ReportReason reason, User user, Post post) {
        this.reason = reason;
        this.timestamp = LocalDate.now();
        this.user = user;
        this.accepted = null;
        this.post = post;
    }
    public Report(ReportReason reason, User user, Comment comment) {
        this.reason = reason;
        this.timestamp = LocalDate.now();
        this.user = user;
        this.accepted = null;
        this.comment = comment;
    }

    public Report(Integer id, ReportReason reason, LocalDate timestamp, User user, Boolean accepted, Post post, Comment comment) {
        this.id = id;
        this.reason = reason;
        this.timestamp = timestamp;
        this.user = user;
        this.accepted = accepted;
        this.post = post;
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", reason=" + reason +
                ", timestamp=" + timestamp +
                ", user=" + user +
                ", accepted=" + accepted +
                ", post=" + post +
                ", comment=" + comment +
                '}';
    }
}
