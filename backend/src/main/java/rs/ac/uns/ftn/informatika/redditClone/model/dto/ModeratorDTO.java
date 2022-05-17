package rs.ac.uns.ftn.informatika.redditClone.model.dto;

import rs.ac.uns.ftn.informatika.redditClone.model.entity.Moderator;

import java.io.Serializable;

public class ModeratorDTO extends UserCreateDTO implements Serializable {
    public ModeratorDTO() {
    }

    public ModeratorDTO(String username, String password, String email, String avatar, String description) {
        super(username, password, email, avatar, description);
    }

    public ModeratorDTO(Moderator moderator){this(moderator.getUsername(), moderator.getPassword(), moderator.getEmail(), moderator.getAvatar(),moderator.getDescription());}

    @Override
    public String toString() {
        return "Moderator{" +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
