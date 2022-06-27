package rs.ac.uns.ftn.informatika.redditClone.service;

import rs.ac.uns.ftn.informatika.redditClone.model.entity.Comment;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Post;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.User;

import java.util.List;

public interface CommentService {
    public Comment findOne(Integer id);
    public List<Comment>findAll();
//    public List<Comment>findByPost(Post post);
    public Comment findParentComment(Comment comment);
    public void deletePostComment(Comment comment);
    public Comment save(Comment comment);
    public void delete(Comment comment);
    public List<Comment> findAllByUser(User user);
}
