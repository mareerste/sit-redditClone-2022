package rs.ac.uns.ftn.informatika.redditClone.model.dto;

import rs.ac.uns.ftn.informatika.redditClone.model.entity.Moderator;

import java.io.Serializable;

public class ModeratorDTO extends UserCreateDTO implements Serializable {
    public ModeratorDTO() {
    }

    public ModeratorDTO(String username, String password, String email, String avatar, String description, String displayName) {
        super(username, password, email, avatar, description,displayName);
    }

    public ModeratorDTO(Moderator moderator){this(moderator.getUsername(), moderator.getPassword(), moderator.getEmail(), moderator.getAvatar(),moderator.getDescription(), moderator.getDisplayName());}

    @Override
    public String toString() {
        return "Moderator{" +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
