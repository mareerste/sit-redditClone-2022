package rs.ac.uns.ftn.informatika.redditClone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rs.ac.uns.ftn.informatika.redditClone.model.Community;
import rs.ac.uns.ftn.informatika.redditClone.model.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {
    public List<Post> findByCommunity(Community community);
    public List<Post> findByCommunityAndIsDeleted(Community community, Boolean isDeleted);

}
