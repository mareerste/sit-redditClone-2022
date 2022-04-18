package rs.ac.uns.ftn.informatika.redditClone.model.dto;

import rs.ac.uns.ftn.informatika.redditClone.model.entity.Moderator;

import java.io.Serializable;

public class ModeratorDTO extends UserCreateDTO implements Serializable {
    public ModeratorDTO() {
    }

    public ModeratorDTO(String username, String email, String description) {
        super(username, email, description);
    }
    public ModeratorDTO(Moderator moderator){this(moderator.getUsername(), moderator.getEmail(), moderator.getDescription());}

    @Override
    public String toString() {
        return "Moderator{" +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
