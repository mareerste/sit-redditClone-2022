package rs.ac.uns.ftn.informatika.redditClone.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Report;
import rs.ac.uns.ftn.informatika.redditClone.model.enumerations.ReactionType;
import rs.ac.uns.ftn.informatika.redditClone.model.enumerations.ReportReason;

import java.io.Serializable;
import java.time.LocalDate;

public class ReportPostDTO implements Serializable {
    private Integer id;
    private ReportReason reason;
    @JsonFormat(pattern = "MM/dd/yyyy", shape = JsonFormat.Shape.OBJECT)
    private LocalDate timestamp;
    private UserCreateDTO user;
    private Boolean accepted;
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
    public PostDTO getPost() {
        return post;
    }
    public void setPost(PostDTO post) {
        this.post = post;
    }

    public ReportPostDTO() {
    }

    public ReportPostDTO(Integer id, ReportReason reason, LocalDate timestamp, UserCreateDTO user, Boolean accepted, PostDTO post) {
        this.id = id;
        this.reason = reason;
        this.timestamp = timestamp;
        this.user = user;
        this.accepted = accepted;
        this.post = post;
    }
    public ReportPostDTO(Report report){this(report.getId(), report.getReason(),report.getTimestamp(),new UserCreateDTO(report.getUser()),report.getAccepted(),new PostDTO(report.getPost()));}

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", reason=" + reason +
                ", timestamp=" + timestamp +
                ", user=" + user +
                ", accepted=" + accepted +
                ", post=" + post +
                '}';
    }

}