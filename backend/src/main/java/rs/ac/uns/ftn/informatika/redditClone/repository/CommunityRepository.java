package rs.ac.uns.ftn.informatika.redditClone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Community;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Post;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.User;

import java.util.List;

public interface CommunityRepository extends JpaRepository<Community,Integer> {

    public List<Community> findByModerators(User user);
    public Community findByPosts(Post post);
}
