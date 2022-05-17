package rs.ac.uns.ftn.informatika.redditClone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
    public User findByUsernameAndPassword(String username, String password);
    public User findByEmail(String email);
    public User findByUsername(String username);
}
