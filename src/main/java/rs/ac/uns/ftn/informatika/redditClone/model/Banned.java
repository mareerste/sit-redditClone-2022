package rs.ac.uns.ftn.informatika.redditClone.model;

import org.springframework.http.HttpInputMessage;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Banned {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "date",nullable = false)
    private LocalDate timestamp;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "moderator_id",nullable = false)
    private Moderator moderator;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "community_id")
    private Community community;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
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

    public Banned() {
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
                ", moderator=" + moderator +
                ", community=" + community +
                ", user=" + user +
                '}';
    }
}
