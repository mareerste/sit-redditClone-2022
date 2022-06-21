package rs.ac.uns.ftn.informatika.redditClone.service;

import rs.ac.uns.ftn.informatika.redditClone.model.entity.Comment;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Post;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Reaction;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.User;

import java.util.List;

public interface ReactionService {
    Reaction findOne(Integer id);
    List<Reaction> findAll();
    List<Reaction> findAllByPost(Post post);
    List<Reaction> findAllByComment(Comment comment);
    Reaction save(Reaction reaction);
    void delete(Reaction reaction);
    List<Reaction> findAllForPosts();
    List<Reaction> findAllForComments();
    List<Reaction> findByCommentAndUser(Comment comment, User user);
    List<Reaction> findByPostAndUser(Post post, User user);

    Long getUsersUpvotes(User user);
    Long getUsersDownvotes(User user);
}
