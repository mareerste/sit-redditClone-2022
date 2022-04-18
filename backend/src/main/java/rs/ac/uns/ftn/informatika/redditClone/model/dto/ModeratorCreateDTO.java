package rs.ac.uns.ftn.informatika.redditClone.model.dto;

import rs.ac.uns.ftn.informatika.redditClone.model.entity.Moderator;

import java.io.Serializable;
import java.time.LocalDate;

public class ModeratorCreateDTO extends UserCreateDTO implements Serializable {
    public ModeratorCreateDTO() {
    }

    public ModeratorCreateDTO(String username, String password, String email, String avatar, LocalDate registrationDate, String description) {
        super(username, password, email, avatar, registrationDate, description);
    }
    public ModeratorCreateDTO(String username, String email, String description) {
        super(username, email, description);
    }
    public ModeratorCreateDTO(Moderator moderator){this(moderator.getUsername(), moderator.getPassword(), moderator.getEmail(), moderator.getAvatar(), moderator.getRegistrationDate(), moderator.getDescription());}

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
