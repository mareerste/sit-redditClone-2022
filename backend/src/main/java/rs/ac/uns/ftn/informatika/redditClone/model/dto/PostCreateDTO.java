package rs.ac.uns.ftn.informatika.redditClone.model.dto;

import rs.ac.uns.ftn.informatika.redditClone.model.entity.Post;

import java.io.Serializable;
import java.time.LocalDate;

public class PostCreateDTO implements Serializable {
    private Integer id;
    private String title;
    private String text;
    private LocalDate creationDate;
    private String imagePath;
    private Boolean isDeleted;
    private UserCreateDTO user;
    private FlairDTO flair;
    private CommunityDTO community;

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
    public CommunityDTO getCommunity() {
        return community;
    }
    public void setCommunity(CommunityDTO community) {
        this.community = community;
    }
    public void setFlair(FlairDTO flair) {this.flair = flair;}
    public PostCreateDTO() {
    }
    public PostCreateDTO(Integer id, String title, String text, LocalDate creationDate, String imagePath, Boolean isDeleted, UserCreateDTO user, FlairDTO flair, CommunityDTO community) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.creationDate = creationDate;
        this.imagePath = imagePath;
        this.isDeleted = isDeleted;
        this.user = user;
        this.flair = flair;
        this.community = community;
    }
    public PostCreateDTO(Post post){this(post.getId(), post.getTitle(), post.getText(), post.getCreationDate(), post.getImagePath(), post.getDeleted(), new UserCreateDTO(post.getUser()),new FlairDTO(post.getFlair()), new CommunityDTO(post.getCommunity()));}

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
