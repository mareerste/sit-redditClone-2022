package rs.ac.uns.ftn.informatika.redditClone.model;

import javax.persistence.Entity;

@Entity
public class Administrator extends User{

    public Administrator() {
    }

    public Administrator(String username, String password, String email, String avatar, String registrationDate, String description) {
        super(username, password, email, avatar, registrationDate, description);
    }
}
