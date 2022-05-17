package rs.ac.uns.ftn.informatika.redditClone.model.dto;

import rs.ac.uns.ftn.informatika.redditClone.model.entity.Administrator;

import java.io.Serializable;
import java.time.LocalDate;


public class AdministratorDTO extends UserCreateDTO implements Serializable {

    public AdministratorDTO() {
    }

    public AdministratorDTO(String username, String password, String email, String avatar, String description) {
        super(username, password, email, avatar, description);
    }

    public AdministratorDTO(Administrator admin){this(admin.getUsername(), admin.getPassword(), admin.getEmail(),admin.getAvatar(), admin.getDescription());}

}
