package rs.ac.uns.ftn.informatika.redditClone.model.dto;

import rs.ac.uns.ftn.informatika.redditClone.model.entity.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;

public class UserDTO implements Serializable {

    protected String username;
    protected String password;
    protected String email;
    protected String avatar;
    protected LocalDate registrationDate;
    protected String description;

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

    public UserDTO() {
    }

    public UserDTO(String username, String password, String email, String avatar, LocalDate registrationDate, String description) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.avatar = avatar;
        this.registrationDate = registrationDate;
        this.description = description;
    }

    public UserDTO(String username, String password, String email, LocalDate registrationDate, String description) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.registrationDate = registrationDate;
        this.description = description;
    }

    public UserDTO(User user){
        this(user.getUsername(), user.getPassword(), user.getEmail(), user.getAvatar(),user.getRegistrationDate(), user.getDescription());
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
