package rs.ac.uns.ftn.informatika.redditClone.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Comment;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class CommentDTO implements Serializable {

    private Integer id;
    private String text;
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.OBJECT)
    private LocalDate timestamp;
    private Boolean isDeleted;
    private UserCreateDTO user;
//    private CommentDTO parentComment;
//    private PostDTO post;
    private Set<Comment> childComments = new HashSet<>();

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
//    public PostDTO getPost() {
//        return post;
//    }
//    public void setPost(PostDTO post) {
//        this.post = post;
//    }
    public UserCreateDTO getUser() {
        return user;
    }
    public void setUser(UserCreateDTO user) {
        this.user = user;
    }
//    public CommentDTO getParentComment() {
//        return parentComment;
//    }
//    public void setParentComment(CommentDTO parentComment) {
//        this.parentComment = parentComment;
//    }
    public Set<Comment> getChildComments() {
        return childComments;
    }
    public void setChildComments(Set<Comment> childComments) {
        this.childComments = childComments;
    }

    public CommentDTO() {
    }

    public CommentDTO(Integer id, String text, LocalDate timestamp, Boolean isDeleted, UserCreateDTO user, Set<Comment> childComments) {
        this.id = id;
        this.text = text;
        this.timestamp = timestamp;
        this.isDeleted = isDeleted;
        this.user = user;
        this.childComments = childComments;
    }

//    public CommentDTO(Comment comment){this(comment.getId(), comment.getText(), comment.getTimestamp(),comment.getDeleted(), new UserCreateDTO(comment.getUser()),(comment.getParentComment() != null && comment.getParentComment().getId() != null) ? new CommentDTO(comment.getParentComment()) : new CommentDTO(),new PostDTO(comment.getPost()));}
//    public CommentDTO(Comment comment){this(comment.getId(), comment.getText(), comment.getTimestamp(),comment.getDeleted(), new UserCreateDTO(comment.getUser()),(comment.getParentComment() != null && comment.getParentComment().getId() != null) ? new CommentDTO(comment.getParentComment()) : null,(comment.getPost() != null && comment.getPost().getId() != null) ? new PostDTO(comment.getPost()) : null);}

    public  CommentDTO(Comment comment){this(comment.getId(), comment.getText(), comment.getTimestamp(),comment.getDeleted(),new UserCreateDTO(comment.getUser()),comment.getChildComments());}

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
