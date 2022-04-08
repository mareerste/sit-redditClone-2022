package rs.ac.uns.ftn.informatika.redditClone.model.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {

    @Id
    @Column(name = "username", unique = true, nullable = false)
    protected String username;
    @Column(name = "password", nullable = false)
    protected String password;
    @Column(name = "email", nullable = false)
    protected String email;
    @Column(name = "avatar", nullable = true)
    protected String avatar;
    @Column(name = "registrationDate", nullable = false)
    protected LocalDate registrationDate;
    @Column(name = "description", nullable = false)
    protected String description;
//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//    protected Set<Comment> comments = new HashSet<>();
//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//    protected Set<Reaction> reactions = new HashSet<>();
//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//    protected Set<Post> posts = new HashSet<>();
//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//    protected Set<Banned> bannedSet = new HashSet<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User() {
    }

//    public Set<Comment> getComments() {
//        return comments;
//    }
//
//    public void setComments(Set<Comment> comments) {
//        this.comments = comments;
//    }
//
//    public Set<Reaction> getReactions() {
//        return reactions;
//    }
//
//    public void setReactions(Set<Reaction> reactions) {
//        this.reactions = reactions;
//    }
//
//    public Set<Post> getPosts() {
//        return posts;
//    }
//
//    public void setPosts(Set<Post> posts) {
//        this.posts = posts;
//    }
//
//    public Set<Banned> getBannedSet() {
//        return bannedSet;
//    }
//
//    public void setBannedSet(Set<Banned> bannedSet) {
//        this.bannedSet = bannedSet;
//    }

    public User(String username, String password, String email, String avatar, LocalDate registrationDate, String description) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.avatar = avatar;
        this.registrationDate = registrationDate;
        this.description = description;
    }

    public User(String username, String password, String email, String avatar, String description) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.avatar = avatar;
        this.registrationDate = LocalDate.now();
        this.description = description;
    }
    public User(String username, String password, String email, String description) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.registrationDate = LocalDate.now();
        this.description = description;
    }
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", registrationDate='" + registrationDate + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
