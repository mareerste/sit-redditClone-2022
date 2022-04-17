package rs.ac.uns.ftn.informatika.redditClone.model.dto;

import rs.ac.uns.ftn.informatika.redditClone.model.entity.Comment;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

public class CommentDTO implements Serializable {

    private Integer id;
    private String text;
    private LocalDate timestamp;
    private Boolean isDeleted;
    private UserDTO user;
    private CommentDTO parentComment;
    private PostDTO post;

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
    public PostDTO getPost() {
        return post;
    }
    public void setPost(PostDTO post) {
        this.post = post;
    }
    public UserDTO getUser() {
        return user;
    }
    public void setUser(UserDTO user) {
        this.user = user;
    }
    public CommentDTO getParentComment() {
        return parentComment;
    }
    public void setParentComment(CommentDTO parentComment) {
        this.parentComment = parentComment;
    }

    public CommentDTO() {
    }

    public CommentDTO(Integer id, String text, LocalDate timestamp, Boolean isDeleted, UserDTO user, CommentDTO parentComment, PostDTO post) {
        this.id = id;
        this.text = text;
        this.timestamp = timestamp;
        this.isDeleted = isDeleted;
        this.user = user;
        this.parentComment = parentComment;
        this.post = post;
    }
    public CommentDTO(Comment comment){this(comment.getId(), comment.getText(), comment.getTimestamp(),comment.getDeleted(), new UserDTO(comment.getUser()),(comment.getParentComment() != null && comment.getParentComment().getId() != null) ? new CommentDTO(comment.getParentComment()) : new CommentDTO(),new PostDTO(comment.getPost()));}

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
