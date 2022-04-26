package rs.ac.uns.ftn.informatika.redditClone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Moderator;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.User;

public interface ModeratorRepository extends JpaRepository<Moderator,String> {
    @Query(nativeQuery = true,value = "UPDATE user SET dtype = 'Moderator' WHERE username = ?1")
    public void updateToModerator(String username);
}
