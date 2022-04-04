package rs.ac.uns.ftn.informatika.redditClone.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "moderator")
public class Moderator extends User{
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Community> communities = new HashSet<>();
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Banned> bannedSet = new HashSet<>();

    public Set<Community> getCommunities() {
        return communities;
    }

    public void setCommunities(Set<Community> communities) {
        this.communities = communities;
    }

    public Set<Banned> getBannedSet() {
        return bannedSet;
    }

    public void setBannedSet(Set<Banned> bannedSet) {
        this.bannedSet = bannedSet;
    }

    public Moderator() {
    }

    public Moderator(String username, String password, String email, String avatar, String registrationDate, String description, Set<Community> communities) {
        super(username, password, email, avatar, registrationDate, description);
        this.communities = communities;
    }

    @Override
    public String toString() {
        return "Moderator{" +
                "bannedSet=" + bannedSet +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", registrationDate='" + registrationDate + '\'' +
                ", description='" + description + '\'' +
                ", comments=" + comments +
                ", reactions=" + reactions +
                ", posts=" + posts +
                ", bannedSet=" + bannedSet +
                '}';
    }
}
