package rs.ac.uns.ftn.informatika.redditClone.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Comment;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Post;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Report;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.User;
import rs.ac.uns.ftn.informatika.redditClone.model.enumerations.ReactionType;
import rs.ac.uns.ftn.informatika.redditClone.model.enumerations.ReportReason;

import java.io.Serializable;
import java.time.LocalDate;

public class ReportDTO implements Serializable {
    private Integer id;
    private ReportReason reason;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate timestamp;
    private UserDTO user;
    private Boolean accepted;
    private CommentDTO comment;
    private PostDTO post;

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

    public CommentDTO getComment() {
        return comment;
    }

    public void setComment(CommentDTO comment) {
        this.comment = comment;
    }

    public PostDTO getPost() {
        return post;
    }

    public void setPost(PostDTO post) {
        this.post = post;
    }

public ReportDTO(){}

    public ReportDTO(Integer id, ReportReason reason, LocalDate timestamp, UserDTO user, Boolean accepted, PostDTO post, CommentDTO comment) {
        this.id = id;
        this.reason = reason;
        this.timestamp = timestamp;
        this.user = user;
        this.accepted = accepted;
        this.post = post;
        this.comment = comment;
    }

    public ReportDTO(Report report){this(report.getId(), report.getReason(), report.getTimestamp() , new UserDTO(report.getUser()), report.getAccepted(), ((report.getPost() == null) ? null: new PostDTO(report.getPost())) , ((report.getComment() == null) ? null:new CommentDTO(report.getComment())));};

    @Override
    public String toString() {
        return "ReportDTO{" +
                "id=" + id +
                ", reason=" + reason +
                ", timestamp=" + timestamp +
                ", user=" + user +
                ", accepted=" + accepted +
                ", comment=" + comment +
                ", post=" + post +
                '}';
    }

}
