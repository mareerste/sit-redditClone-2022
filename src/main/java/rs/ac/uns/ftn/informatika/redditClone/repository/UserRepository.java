package rs.ac.uns.ftn.informatika.redditClone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.informatika.redditClone.model.User;

public interface UserRepository extends JpaRepository<User, String> {
    public User findByUsernameAndPassword(String username, String password);
}
