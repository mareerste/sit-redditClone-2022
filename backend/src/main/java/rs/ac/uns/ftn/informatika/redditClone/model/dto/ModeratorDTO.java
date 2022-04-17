package rs.ac.uns.ftn.informatika.redditClone.model.dto;

import rs.ac.uns.ftn.informatika.redditClone.model.entity.Moderator;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.User;

import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;
public class ModeratorDTO extends UserDTO implements Serializable {
    public ModeratorDTO() {
    }

    public ModeratorDTO(String username, String password, String email, String avatar, LocalDate registrationDate, String description) {
        super(username, password, email, avatar, registrationDate, description);
    }
    public ModeratorDTO(Moderator moderator){this(moderator.getUsername(), moderator.getPassword(), moderator.getEmail(), moderator.getAvatar(), moderator.getRegistrationDate(), moderator.getDescription());}

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
