package rs.ac.uns.ftn.informatika.redditClone.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Comment;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Post;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Reaction;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.User;
import rs.ac.uns.ftn.informatika.redditClone.repository.ReactionRepository;
import rs.ac.uns.ftn.informatika.redditClone.service.ReactionService;

import java.util.List;

@Service
public class ReactionServiceImpl implements ReactionService {
    @Autowired
    private ReactionRepository reactionRepository;

    @Override
    public Reaction findOne(Integer id){return reactionRepository.findById(id).orElse(null);}
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

    @Override
    public List<Reaction> findAllForPosts() {
        return reactionRepository.findAllForPosts();
    }

    @Override
    public List<Reaction> findAllForComments() {
        return reactionRepository.findAllForComments();
    }

    @Override
    public List<Reaction> findByCommentAndUser(Comment comment, User user) {
        return reactionRepository.findByCommentAndUser(comment,user);
    }

    @Override
    public List<Reaction> findByPostAndUser(Post post, User user) {
        return reactionRepository.findByPostAndUser(post,user);
    }
}
