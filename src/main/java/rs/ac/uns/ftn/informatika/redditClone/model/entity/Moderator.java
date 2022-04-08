package rs.ac.uns.ftn.informatika.redditClone.model.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Moderator extends User{
//    @ManyToMany(mappedBy = "moderators",fetch = FetchType.EAGER)
//    private Set<Community> communities = new HashSet<>();
//    @OneToMany
//    private Set<Banned> bannedSet = new HashSet<>();

//    public Set<Community> getCommunities() {
//        return communities;
//    }

//    public void setCommunities(Set<Community> communities) {
//        this.communities = communities;
//    }

    public Moderator() {
    }

    public Moderator(String username, String password, String email, String avatar, LocalDate registrationDate, String description, Set<Community> communities) {
        super(username, password, email, avatar, registrationDate, description);
//        this.communities = communities;
    }

    public Moderator(String username, String password, String email, String description) {
        super(username, password, email, description);
    }

    @Override
    public String toString() {
        return "Moderator{" +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", registrationDate='" + registrationDate + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
