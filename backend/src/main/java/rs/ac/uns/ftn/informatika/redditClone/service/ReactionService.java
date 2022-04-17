package rs.ac.uns.ftn.informatika.redditClone.service;

import rs.ac.uns.ftn.informatika.redditClone.model.entity.Comment;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Post;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Reaction;

import java.util.List;

public interface ReactionService {
    Reaction findOne(Integer id);
    List<Reaction> findAll();
    List<Reaction> findAllByPost(Post post);
    List<Reaction> findAllByComment(Comment comment);
    Reaction save(Reaction reaction);
    void delete(Reaction reaction);
}
