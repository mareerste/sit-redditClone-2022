package rs.ac.uns.ftn.informatika.redditClone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Comment;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Post;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
    public List<Comment> findByPost(Post post);

    public List<Comment> findByParentComment(Comment comment);
}
