package rs.ac.uns.ftn.informatika.redditClone.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Comment;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Post;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Reaction;
import rs.ac.uns.ftn.informatika.redditClone.repository.ReactionRepository;
import rs.ac.uns.ftn.informatika.redditClone.service.ReactionService;

import java.util.List;

@Service
public class ReactionServiceImpl implements ReactionService {
    @Autowired
    private ReactionRepository reactionRepository;

    @Override
    public Reaction findOne(Integer id){return reactionRepository.findById(id).orElseGet(null);}
    @Override
    public List<Reaction> findAll(){return reactionRepository.findAll();}
    @Override
    public List<Reaction> findAllByPost(Post post){return reactionRepository.findAllByPost(post);}
    @Override
    public List<Reaction> findAllByComment(Comment comment){return reactionRepository.findAllByComment(comment);}
    @Override
    public Reaction save(Reaction reaction){return reactionRepository.save(reaction);}
    @Override
    public void delete(Reaction reaction){reactionRepository.delete(reaction);}
}
