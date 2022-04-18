package rs.ac.uns.ftn.informatika.redditClone.model.dto;

import rs.ac.uns.ftn.informatika.redditClone.model.entity.User;

import java.io.Serializable;
import java.time.LocalDate;

public class UserDTO implements Serializable {
    protected String username;
    protected String email;
    protected String avatar;
    protected String description;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserDTO() {
    }

    public UserDTO(String username, String email, String avatar, String description) {
        this.username = username;
        this.email = email;
        this.avatar = avatar;
        this.description = description;
    }

    public UserDTO(User user){
        this(user.getUsername(), user.getEmail(), user.getAvatar(), user.getDescription());
    }
}
