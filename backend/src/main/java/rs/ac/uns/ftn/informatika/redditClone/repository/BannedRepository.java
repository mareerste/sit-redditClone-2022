package rs.ac.uns.ftn.informatika.redditClone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Banned;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Community;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.User;

public interface BannedRepository extends JpaRepository<Banned,Integer> {
    public Banned findByUser(User user);
    public Banned findByUserAndCommunity(User user, Community community);
}
