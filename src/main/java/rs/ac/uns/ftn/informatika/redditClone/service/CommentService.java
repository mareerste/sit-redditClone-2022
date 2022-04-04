package rs.ac.uns.ftn.informatika.redditClone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.informatika.redditClone.model.Comment;
import rs.ac.uns.ftn.informatika.redditClone.model.Post;
import rs.ac.uns.ftn.informatika.redditClone.repository.CommentRepository;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Comment findOne(Integer id){return commentRepository.findById(id).orElseGet(null);}
    public List<Comment>findAll(){return commentRepository.findAll();}
    public List<Comment>findByPost(Post post){return commentRepository.findByPost(post);}
    public List<Comment>findByParComments(Comment comment){return commentRepository.findByParentComment(comment);}
    public Comment save(Comment comment){return commentRepository.save(comment);}
    public void delete(Comment comment){ commentRepository.delete(comment);}

}
