package rs.ac.uns.ftn.informatika.redditClone.model;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Administrator extends User{

    public Administrator() {
    }

    public Administrator(String username, String password, String email, String avatar, LocalDate registrationDate, String description) {
        super(username, password, email, avatar, registrationDate, description);
    }
}
