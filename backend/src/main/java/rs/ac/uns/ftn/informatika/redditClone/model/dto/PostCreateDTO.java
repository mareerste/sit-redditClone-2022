package rs.ac.uns.ftn.informatika.redditClone.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Comment;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Post;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class PostCreateDTO implements Serializable {
    private Integer id;
    private String title;
    private String text;
    @JsonFormat(pattern = "MM/dd/yyyy", shape = JsonFormat.Shape.OBJECT)
    private LocalDate creationDate;
    private String imagePath;
    private Boolean isDeleted;
    private UserCreateDTO user;
    private FlairCreateDTO flair;
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
    public FlairCreateDTO getFlair() {
        return flair;
    }
    public Boolean getDeleted() {
        return isDeleted;
    }
    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
    public void setFlair(FlairCreateDTO flair) {this.flair = flair;}

    public Integer getReactions() {
        return reactions;
    }

    public void setReactions(Integer reactions) {
        this.reactions = reactions;
    }

    public Set<CommentDTO> getComments() {
        return comments;
    }
    public void setComments(Set<CommentDTO> comments) {
        this.comments = comments;
    }

    public PostCreateDTO() {
    }
    public PostCreateDTO(Integer id, String title, String text, String imagePath, UserCreateDTO user, FlairCreateDTO flair, Set<Comment>comments, Integer reactions) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.creationDate = LocalDate.now();
        this.imagePath = imagePath;
        this.isDeleted = false;
        this.user = user;
        this.flair = flair;
        this.comments = loadComments(comments);
        this.reactions = reactions;
    }
    private Set<CommentDTO> loadComments(Set<Comment>comments){
        Set<CommentDTO> newComments = new HashSet<>();
        for (Comment c: comments) {
            newComments.add(new CommentDTO(c));
        }
        return newComments;
    }
    public PostCreateDTO(Post post){this(post.getId(), post.getTitle(), post.getText(), post.getImagePath(), new UserCreateDTO(post.getUser()),new FlairCreateDTO(post.getFlair()), post.getComments(), post.getReactions().size());}

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
