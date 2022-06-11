package rs.ac.uns.ftn.informatika.redditClone.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Post;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Reaction;
import rs.ac.uns.ftn.informatika.redditClone.model.enumerations.ReactionType;

import java.io.Serializable;
import java.time.LocalDate;

public class ReactionDTO implements Serializable {
    private Integer id;
    private ReactionType type;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate timestamp;
    private UserCreateDTO user;
    private Integer post;
    private Integer comment;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public ReactionType getType() {
        return type;
    }
    public void setType(ReactionType type) {
        this.type = type;
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

    public Integer getPost() {
        return post;
    }

    public void setPost(Integer post) {
        this.post = post;
    }

    public Integer getComment() {
        return comment;
    }

    public void setComment(Integer comment) {
        this.comment = comment;
    }

    public ReactionDTO() {
    }

    public ReactionDTO(Integer id, ReactionType type, LocalDate timestamp, UserCreateDTO user, Integer postDTO, Integer commentDTO) {
        this.id = id;
        this.type = type;
        this.timestamp = timestamp;
        this.user = user;
        this.post = postDTO;
        this.comment = commentDTO;
    }
    public ReactionDTO(Reaction reaction){this(reaction.getId(), reaction.getType(),reaction.getTimestamp(),new UserCreateDTO(reaction.getUser()), ((reaction.getPost() == null) ? null : reaction.getPost().getId()), ((reaction.getComment() == null) ? null : reaction.getComment().getId()));}
    @Override
    public String toString() {
        return "Reaction{" +
                "id=" + id +
                ", type=" + type +
                ", timestamp=" + timestamp +
                ", user=" + user +
                '}';
    }
}
