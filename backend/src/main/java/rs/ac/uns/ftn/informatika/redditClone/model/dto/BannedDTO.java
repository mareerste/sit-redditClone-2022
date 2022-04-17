package rs.ac.uns.ftn.informatika.redditClone.model.dto;

import rs.ac.uns.ftn.informatika.redditClone.model.entity.Banned;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

public class BannedDTO implements Serializable {

    private Integer id;
    private LocalDate timestamp;
    private ModeratorDTO moderator;
    private CommunityDTO community;
    private UserDTO user;

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
    public ModeratorDTO getModerator() {
        return moderator;
    }
    public void setModerator(ModeratorDTO moderator) {
        this.moderator = moderator;
    }
    public CommunityDTO getCommunity() {
        return community;
    }
    public void setCommunity(CommunityDTO community) {
        this.community = community;
    }
    public UserDTO getUser() {
        return user;
    }
    public void setUser(UserDTO user) {
        this.user = user;
    }
    public BannedDTO(){
    }

    public BannedDTO(Integer id, LocalDate timestamp, ModeratorDTO moderator, CommunityDTO community, UserDTO user) {
        this.id = id;
        this.timestamp = timestamp;
        this.moderator = moderator;
        this.community = community;
        this.user = user;
    }
    public BannedDTO(Banned banned){this(banned.getId(),banned.getTimestamp(),new ModeratorDTO(banned.getModerator()),new CommunityDTO(banned.getCommunity()),new UserDTO(banned.getUser()));}

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
