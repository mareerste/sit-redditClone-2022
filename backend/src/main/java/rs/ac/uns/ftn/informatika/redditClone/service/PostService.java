package rs.ac.uns.ftn.informatika.redditClone.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Community;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Post;

import java.util.List;

public interface PostService {
    Post findOne(Integer id);
    List<Post> findAll();
    Page<Post> findAll(Pageable pageable);
    List<Post> findAllforCommunity(Community community);
    List<Post> findUndeletedForCommunity(Community community, Boolean isDeleted);
    Post save(Post post);
    void delete(Post post);
}
