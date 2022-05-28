package rs.ac.uns.ftn.informatika.redditClone.model.dto;

import rs.ac.uns.ftn.informatika.redditClone.model.entity.User;

import java.io.Serializable;
import java.time.LocalDate;

public class UserCreateDTO implements Serializable {

    protected String username;
    protected String password;
    protected String email;
    protected String avatar;
    protected LocalDate registrationDate = LocalDate.now();
    protected String description;
    protected String displayName;

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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public UserCreateDTO() {
    }

    public UserCreateDTO(String username, String password, String email, String avatar, String description, String displayName) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.avatar = avatar;
        this.registrationDate = LocalDate.now();
        this.description = description;
        this.displayName = displayName;
    }


    public UserCreateDTO(User user){
        this(user.getUsername(), user.getPassword(), user.getEmail(), user.getAvatar(), user.getDescription(), user.getDisplayName());
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
