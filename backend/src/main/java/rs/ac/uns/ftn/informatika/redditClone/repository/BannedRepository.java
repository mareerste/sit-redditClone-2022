package rs.ac.uns.ftn.informatika.redditClone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Banned;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Community;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.User;

import java.util.List;

public interface BannedRepository extends JpaRepository<Banned,Integer> {
    public List<Banned> findByUser(User user);
    public List<Banned> findByCommunity(Community community);
    public Banned findByUserAndCommunity(User user, Community community);
}
