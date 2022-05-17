package rs.ac.uns.ftn.informatika.redditClone.model.entity;

import javax.persistence.*;
import java.lang.annotation.Inherited;
import java.time.LocalDate;
import java.util.Set;

@Entity
//@Table(name = "ModeratorsEntity")
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorValue("moderator")
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

    public Moderator(String username, String password, String email, String avatar, LocalDate registrationDate, String description, Boolean deleted) {
        super(username, password, email, avatar, registrationDate, description, deleted);
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
