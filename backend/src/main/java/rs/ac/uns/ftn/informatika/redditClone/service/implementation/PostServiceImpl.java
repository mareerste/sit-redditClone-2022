package rs.ac.uns.ftn.informatika.redditClone.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.informatika.redditClone.model.dto.PostCreateDTO;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Community;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Post;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.User;
import rs.ac.uns.ftn.informatika.redditClone.repository.PostRepository;
import rs.ac.uns.ftn.informatika.redditClone.service.FlairService;
import rs.ac.uns.ftn.informatika.redditClone.service.PostService;
import rs.ac.uns.ftn.informatika.redditClone.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private FlairService flairService;
    @Override
    public Post findOne(Integer id){return postRepository.findById(id).orElse(null);}
    @Override
    public List<Post> findAll(){return postRepository.findAll();}
    @Override
    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }
    @Override
    public Post save(Post post){return postRepository.save(post);}

    @Override
    public Post save(PostCreateDTO post) {
        Post newPost = new Post();
        newPost.setTitle(post.getTitle());
        newPost.setText(post.getText());
        newPost.setImagePath(post.getImagePath());
        newPost.setUser(userService.findOne(post.getUser().getUsername()));
        newPost.setFlair(flairService.findOne(post.getFlair().getId()));
        newPost.setComments(post.getComments());
        save(newPost);
        return newPost;
    }

    @Override
    public void delete(Post post){postRepository.delete(post);}
}
