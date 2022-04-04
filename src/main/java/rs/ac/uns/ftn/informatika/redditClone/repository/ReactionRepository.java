package rs.ac.uns.ftn.informatika.redditClone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.informatika.redditClone.model.Comment;
import rs.ac.uns.ftn.informatika.redditClone.model.Post;
import rs.ac.uns.ftn.informatika.redditClone.model.Reaction;

import java.util.List;

public interface ReactionRepository extends JpaRepository<Reaction,Integer> {

    public List<Reaction> findAllByPost(Post post);
    public List<Reaction> findAllByComment(Comment comment);
}
