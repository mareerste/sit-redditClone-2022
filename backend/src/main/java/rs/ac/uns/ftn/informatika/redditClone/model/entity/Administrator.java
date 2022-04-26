package rs.ac.uns.ftn.informatika.redditClone.model.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Administrator extends User{

    public Administrator() {
    }

    public Administrator(String username, String password, String email, String avatar, LocalDate registrationDate, String description, Boolean deleted) {
        super(username, password, email, avatar, registrationDate, description, deleted);
    }
}
