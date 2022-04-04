package rs.ac.uns.ftn.informatika.redditClone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.informatika.redditClone.model.Banned;
import rs.ac.uns.ftn.informatika.redditClone.model.User;

public interface BannedRepository extends JpaRepository<Banned,Integer> {
    public Banned findByUser(User user);
}
