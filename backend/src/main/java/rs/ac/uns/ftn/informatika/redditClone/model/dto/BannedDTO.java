package rs.ac.uns.ftn.informatika.redditClone.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Banned;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.User;

import java.io.Serializable;
import java.time.LocalDate;

public class BannedDTO implements Serializable {

    private Integer id;
    @JsonFormat(pattern = "MM/dd/yyyy", shape = JsonFormat.Shape.OBJECT)
    private LocalDate timestamp;
    private UserDTO moderator;
    private CommunityDTO community;
    private UserCreateDTO user;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public LocalDate getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }
    public UserDTO getModerator() {
        return moderator;
    }
    public void setModerator(UserDTO moderator) {
        this.moderator = moderator;
    }
    public CommunityDTO getCommunity() {
        return community;
    }
    public void setCommunity(CommunityDTO community) {
        this.community = community;
    }
    public UserCreateDTO getUser() {
        return user;
    }
    public void setUser(UserCreateDTO user) {
        this.user = user;
    }
    public BannedDTO(){
    }

    public BannedDTO(Integer id, UserDTO moderator, CommunityDTO community, UserCreateDTO user) {
        this.id = id;
        this.timestamp = LocalDate.now();
        this.moderator = moderator;
        this.community = community;
        this.user = user;
    }
    public BannedDTO(Banned banned){this(banned.getId(),new UserDTO(banned.getModerator()),new CommunityDTO(banned.getCommunity()),new UserCreateDTO(banned.getUser()));}

    @Override
    public String toString() {
        return "Banned{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", moderator=" + moderator.getUsername() +
                ", community=" + community.getName() +
                ", user=" + user.getUsername() +
                '}';
    }
}
