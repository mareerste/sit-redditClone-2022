package rs.ac.uns.ftn.informatika.redditClone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.informatika.redditClone.model.Comment;
import rs.ac.uns.ftn.informatika.redditClone.model.Post;
import rs.ac.uns.ftn.informatika.redditClone.model.Reaction;
import rs.ac.uns.ftn.informatika.redditClone.repository.ReactionRepository;

import java.util.List;

@Service
public class ReactionService {
    @Autowired
    private ReactionRepository reactionRepository;

    public Reaction findOne(Integer id){return reactionRepository.findById(id).orElseGet(null);}
    public List<Reaction> findAll(){return reactionRepository.findAll();}
    public List<Reaction> findAllByPost(Post post){return reactionRepository.findAllByPost(post);}
    public List<Reaction> findAllByComment(Comment comment){return reactionRepository.findAllByComment(comment);}
    public Reaction save(Reaction reaction){return reactionRepository.save(reaction);}
    public void delete(Reaction reaction){reactionRepository.delete(reaction);}
}
