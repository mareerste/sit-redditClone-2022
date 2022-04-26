package rs.ac.uns.ftn.informatika.redditClone.service;

import rs.ac.uns.ftn.informatika.redditClone.model.entity.Comment;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Post;

import java.util.List;

public interface CommentService {
    public Comment findOne(Integer id);
    public List<Comment>findAll();
    public List<Comment>findByPost(Post post);
    public List<Comment> findByParentComment(Comment comment);
    public Comment save(Comment comment);
    public void delete(Comment comment);
}
