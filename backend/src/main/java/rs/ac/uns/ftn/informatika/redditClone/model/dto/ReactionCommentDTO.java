package rs.ac.uns.ftn.informatika.redditClone.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Reaction;
import rs.ac.uns.ftn.informatika.redditClone.model.enumerations.ReactionType;

import java.io.Serializable;
import java.time.LocalDate;

public class ReactionCommentDTO implements Serializable {
    private Integer id;
    private ReactionType type;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate timestamp;
    private UserCreateDTO user;

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

    public ReactionCommentDTO() {
    }

    public ReactionCommentDTO(Integer id, ReactionType type, LocalDate timestamp, UserCreateDTO user) {
        this.id = id;
        this.type = type;
        this.timestamp = timestamp;
        this.user = user;
    }
    public ReactionCommentDTO(Reaction reaction){this(reaction.getId(), reaction.getType(),reaction.getTimestamp(),new UserCreateDTO(reaction.getUser()));}
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
