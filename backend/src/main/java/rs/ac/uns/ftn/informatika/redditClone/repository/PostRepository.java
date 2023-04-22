package rs.ac.uns.ftn.informatika.redditClone.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Comment;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Community;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Post;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.User;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {
    public Page<Post> findAll(Pageable pageable);
    public List<Post> findAllByUser(User user);
    public Post findByComments(Comment comment);
}
