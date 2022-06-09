package rs.ac.uns.ftn.informatika.redditClone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Comment;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Post;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
//    public List<Comment> findByPost(Post post);

//    select c.id,c.deleted,c.text,c.timestamp, c.user_id from Comment c inner join child_comments child on c.id = child.comment_id where child_id = 2;
//    @Query("SELECT Comment.id FROM Comment INNER JOIN child_comments ON c.id = child_comments.comment_id WHERE child_comments.child_id = :#{#comment.id}")
    @Query(value = "select c.id,c.deleted,c.text,c.timestamp, c.user_id from Comment c inner join child_comments child on c.id = child.comment_id where child_id =?1",nativeQuery = true)
    public List<Comment> findParentComment(Integer commentId);
}
