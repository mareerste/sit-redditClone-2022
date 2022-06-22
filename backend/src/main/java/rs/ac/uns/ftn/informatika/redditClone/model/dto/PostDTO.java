package rs.ac.uns.ftn.informatika.redditClone.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Comment;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Post;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class PostDTO implements Serializable {
    private Integer id;
    private String title;
    private String text;
    @JsonFormat(pattern = "MM/dd/yyyy", shape = JsonFormat.Shape.OBJECT)
    private LocalDate creationDate;
    private String imagePath;
    private Boolean isDeleted;
    private UserCreateDTO user;
    private FlairDTO flair;
    private Set<CommentDTO> comments = new HashSet<>();
    private Integer reactions;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public LocalDate getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
    public String getImagePath() {
        return imagePath;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    public UserCreateDTO getUser() {
        return user;
    }
    public void setUser(UserCreateDTO user) {
        this.user = user;
    }
    public FlairDTO getFlair() {
        return flair;
    }
    public void setFlairs(FlairDTO flairs) {
        this.flair = flairs;
    }
    public Boolean getDeleted() {
        return isDeleted;
    }
    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
    public void setFlair(FlairDTO flair) {this.flair = flair;}
    public Set<CommentDTO> getComments() {
        return comments;
    }
    public void setComments(Set<CommentDTO> comments) {
        this.comments = comments;
    }

    public Integer getReactions() {
        return reactions;
    }

    public void setReactions(Integer reactions) {
        this.reactions = reactions;
    }

    public PostDTO() {
    }
    public PostDTO(Integer id, String title, String text, LocalDate creationDate, String imagePath, Boolean isDeleted, UserCreateDTO user, FlairDTO flair, Set<Comment> comments, Integer reactions) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.creationDate = creationDate;
        this.imagePath = imagePath;
        this.isDeleted = isDeleted;
        this.user = user;
        this.flair = flair;
        this.comments = loadComments(comments);
        this.reactions = reactions;
    }
    public PostDTO(Post post){this(post.getId(), post.getTitle(), post.getText(), post.getCreationDate(), post.getImagePath(), post.getDeleted(), new UserCreateDTO(post.getUser()),new FlairDTO(post.getFlair()), post.getComments(), post.getReactions().size());}
    private Set<CommentDTO> loadComments(Set<Comment>comments){
            Set<CommentDTO> newComments = new HashSet<>();
        for (Comment c: comments) {
            newComments.add(new CommentDTO(c));
        }
        return newComments;
    }
    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", creationDate=" + creationDate +
                ", imagePath='" + imagePath + '\'' +
                ", user=" + user +
                ", flair=" + flair +
                '}';
    }
}
