package rs.ac.uns.ftn.informatika.redditClone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Comment;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Post;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Reaction;

import java.util.List;

public interface ReactionRepository extends JpaRepository<Reaction,Integer> {

    public List<Reaction> findAllByPost(Post post);
    public List<Reaction> findAllByComment(Comment comment);
}
