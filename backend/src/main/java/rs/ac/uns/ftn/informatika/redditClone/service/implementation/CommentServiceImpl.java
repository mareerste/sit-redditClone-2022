package rs.ac.uns.ftn.informatika.redditClone.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Comment;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Post;
import rs.ac.uns.ftn.informatika.redditClone.repository.CommentRepository;
import rs.ac.uns.ftn.informatika.redditClone.service.CommentService;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Override
    public Comment findOne(Integer id){return commentRepository.findById(id).orElseGet(null);}
    @Override
    public List<Comment>findAll(){return commentRepository.findAll();}
    @Override
    public List<Comment>findByPost(Post post){return commentRepository.findByPost(post);}
    @Override
    public List<Comment>findByParentComment(Comment comment){return commentRepository.findByParentComment(comment);}
    @Override
    public Comment save(Comment comment){return commentRepository.save(comment);}
    @Override
    public void delete(Comment comment){ commentRepository.delete(comment);}

}
