package rs.ac.uns.ftn.informatika.redditClone.model.dto;

import rs.ac.uns.ftn.informatika.redditClone.model.entity.Reaction;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Report;
import rs.ac.uns.ftn.informatika.redditClone.model.enumerations.ReactionType;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

public class ReportDTO implements Serializable {

    private Integer id;
    private ReactionType reason;
    private LocalDate timestamp;
    private UserDTO user;
    private Boolean accepted;
    private PostDTO post;
    private CommentDTO comment;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public ReactionType getReason() {
        return reason;
    }
    public void setReason(ReactionType reason) {
        this.reason = reason;
    }
    public LocalDate getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }
    public UserDTO getUser() {
        return user;
    }
    public void setUser(UserDTO user) {
        this.user = user;
    }
    public Boolean getAccepted() {
        return accepted;
    }
    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }
    public PostDTO getPost() {
        return post;
    }
    public void setPost(PostDTO post) {
        this.post = post;
    }
    public CommentDTO getComment() {
        return comment;
    }
    public void setComment(CommentDTO comment) {
        this.comment = comment;
    }

    public ReportDTO() {
    }

    public ReportDTO(Integer id, ReactionType reason, LocalDate timestamp, UserDTO user, Boolean accepted, PostDTO post, CommentDTO comment) {
        this.id = id;
        this.reason = reason;
        this.timestamp = timestamp;
        this.user = user;
        this.accepted = accepted;
        this.post = post;
        this.comment = comment;
    }
    public ReportDTO (Report report){this(report.getId(), report.getReason(),report.getTimestamp(),new UserDTO(report.getUser()),report.getAccepted(),new PostDTO(report.getPost()), new CommentDTO(report.getComment()));}

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
