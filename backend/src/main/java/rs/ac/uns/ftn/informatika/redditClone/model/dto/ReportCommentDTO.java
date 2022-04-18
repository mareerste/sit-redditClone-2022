package rs.ac.uns.ftn.informatika.redditClone.model.dto;

import rs.ac.uns.ftn.informatika.redditClone.model.entity.Report;
import rs.ac.uns.ftn.informatika.redditClone.model.enumerations.ReactionType;

import java.io.Serializable;
import java.time.LocalDate;

public class ReportCommentDTO implements Serializable {

    private Integer id;
    private ReactionType reason;
    private LocalDate timestamp;
    private UserCreateDTO user;
    private Boolean accepted;
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
    public UserCreateDTO getUser() {
        return user;
    }
    public void setUser(UserCreateDTO user) {
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

    public ReportCommentDTO() {
    }

    public ReportCommentDTO(Integer id, ReactionType reason, LocalDate timestamp, UserCreateDTO user, Boolean accepted, CommentDTO comment) {
        this.id = id;
        this.reason = reason;
        this.timestamp = timestamp;
        this.user = user;
        this.accepted = accepted;
        this.comment = comment;
    }
    public ReportCommentDTO(Report report){this(report.getId(), report.getReason(),report.getTimestamp(),new UserCreateDTO(report.getUser()),report.getAccepted(), new CommentDTO(report.getComment()));}

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", reason=" + reason +
                ", timestamp=" + timestamp +
                ", user=" + user +
                ", accepted=" + accepted +
                ", comment=" + comment +
                '}';
    }
}
