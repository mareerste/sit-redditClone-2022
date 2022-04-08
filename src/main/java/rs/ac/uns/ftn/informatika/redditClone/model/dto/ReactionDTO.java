package rs.ac.uns.ftn.informatika.redditClone.model.dto;

import rs.ac.uns.ftn.informatika.redditClone.model.entity.Reaction;
import rs.ac.uns.ftn.informatika.redditClone.model.enumerations.ReactionType;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

public class ReactionDTO implements Serializable {
    private Integer id;
    private ReactionType type;
    private LocalDate timestamp;
    private UserDTO user;
    private PostDTO post;
    private CommentDTO comment;

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
    public UserDTO getUser() {
        return user;
    }
    public void setUser(UserDTO user) {
        this.user = user;
    }
    public PostDTO getPost() {
        return post;
    }
    public void setPost(PostDTO post) {
        this.post = post;
    }
    public CommentDTO getComment() {
        return comment;
    }
    public void setComment(CommentDTO comment) {
        this.comment = comment;
    }

    public ReactionDTO() {
    }

    public ReactionDTO(Integer id, ReactionType type, LocalDate timestamp, UserDTO user, PostDTO post, CommentDTO comment) {
        this.id = id;
        this.type = type;
        this.timestamp = timestamp;
        this.user = user;
        this.post = post;
        this.comment = comment;
    }
    public ReactionDTO(Reaction reaction){this(reaction.getId(), reaction.getType(),reaction.getTimestamp(),new UserDTO(reaction.getUser()),new PostDTO(reaction.getPost()),new CommentDTO(reaction.getComment()));}
    @Override
    public String toString() {
        return "Reaction{" +
                "id=" + id +
                ", type=" + type +
                ", timestamp=" + timestamp +
                ", user=" + user +
                ", post=" + post +
                ", comment=" + comment +
                '}';
    }
}
