package rs.ac.uns.ftn.informatika.redditClone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.informatika.redditClone.model.Community;
import rs.ac.uns.ftn.informatika.redditClone.model.Post;
import rs.ac.uns.ftn.informatika.redditClone.repository.PostRepository;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public Post findOne(Integer id){return postRepository.findById(id).orElseGet(null);}
    public List<Post> findAll(){return postRepository.findAll();}
    public List<Post> findAllforCommunity(Community community){return postRepository.findByCommunity(community);}
    public List<Post> findUndeletedForCommunity(Community community, Boolean isDeleted){return  postRepository.findByCommunityAndIsDeleted(community,isDeleted);}
    public Post save(Post post){return postRepository.save(post);}
    public void delete(Post post){postRepository.delete(post);}
}
