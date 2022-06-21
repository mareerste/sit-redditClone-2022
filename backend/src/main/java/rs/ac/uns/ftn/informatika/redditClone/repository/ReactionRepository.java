package rs.ac.uns.ftn.informatika.redditClone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Comment;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Post;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Reaction;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.User;

import java.util.List;

public interface ReactionRepository extends JpaRepository<Reaction,Integer> {

    public List<Reaction> findAllByPost(Post post);
    public List<Reaction> findAllByComment(Comment comment);
    @Query(nativeQuery = true,value = "select * from reaction where post_id is not null")
    public List<Reaction> findAllForPosts();
    @Query(nativeQuery = true,value = "select * from reaction where comment_id is not null")
    public List<Reaction> findAllForComments();
    public List<Reaction> findByCommentAndUser(Comment comment, User user);
    public List<Reaction> findByPostAndUser(Post post, User user);

    @Query(nativeQuery = true,value = "select count(*) from reaction r left join post p on r.post_id = p.id left join comment c on r.comment_id = c.id where (p.user_id =:username or c.user_id =:username) and r.type = 0")
    public long getUsersUpvotes(@Param("username") String username);

    @Query(nativeQuery = true,value = "select count(*) from reaction r left join post p on r.post_id = p.id left join comment c on r.comment_id = c.id where (p.user_id =:username or c.user_id =:username) and r.type = 1")
    public long getUsersDownvotes(@Param("username") String username);
}
