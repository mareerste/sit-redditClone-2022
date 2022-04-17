package rs.ac.uns.ftn.informatika.redditClone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
    public User findByUsernameAndPassword(String username, String password);
}
