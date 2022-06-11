package rs.ac.uns.ftn.informatika.redditClone.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Banned {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "date")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate timestamp;
    @ManyToOne
    @JoinColumn(name = "moderator_id",referencedColumnName = "username", nullable = true)
    private Moderator moderator;
    @ManyToOne
    @JoinColumn(name = "community_id",referencedColumnName = "id", nullable = true)
    private Community community;
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "username", nullable = true)
    private User user;

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
    public Moderator getModerator() {
        return moderator;
    }
    public void setModerator(Moderator moderator) {
        this.moderator = moderator;
    }
    public Community getCommunity() {
        return community;
    }
    public void setCommunity(Community community) {
        this.community = community;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Banned(){
    }
    //create
    public Banned(Moderator moderator, Community community, User user) {
        this.timestamp = LocalDate.now();
        this.moderator = moderator;
        this.community = community;
        this.user = user;
    }

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
