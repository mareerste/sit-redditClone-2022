package rs.ac.uns.ftn.informatika.redditClone.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Comment;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Post;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
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
    private MultipartFile[] files;
    private Integer karma = 0;

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
