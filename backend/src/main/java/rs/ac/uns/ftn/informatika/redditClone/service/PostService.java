package rs.ac.uns.ftn.informatika.redditClone.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rs.ac.uns.ftn.informatika.redditClone.model.dto.PostCreateDTO;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Community;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Post;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.User;

import java.util.List;

public interface PostService {
    Post findOne(Integer id);
    List<Post> findAll();
    Page<Post> findAll(Pageable pageable);
    List<Post> findAllByUser(User user);
    Post save(Post post);
    Post save(PostCreateDTO post);
    void delete(Post post);
}
