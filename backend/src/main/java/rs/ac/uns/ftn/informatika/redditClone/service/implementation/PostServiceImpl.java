package rs.ac.uns.ftn.informatika.redditClone.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Community;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Post;
import rs.ac.uns.ftn.informatika.redditClone.repository.PostRepository;
import rs.ac.uns.ftn.informatika.redditClone.service.PostService;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Override
    public Post findOne(Integer id){return postRepository.findById(id).orElseGet(null);}
    @Override
    public List<Post> findAll(){return postRepository.findAll();}
    @Override
    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }
    @Override
    public List<Post> findAllforCommunity(Community community){return postRepository.findByCommunity(community);}
    @Override
    public List<Post> findUndeletedForCommunity(Community community, Boolean isDeleted){return  postRepository.findByCommunityAndIsDeleted(community,isDeleted);}
    @Override
    public Post save(Post post){return postRepository.save(post);}
    @Override
    public void delete(Post post){postRepository.delete(post);}
}
