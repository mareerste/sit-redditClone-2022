package rs.ac.uns.ftn.informatika.redditClone.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Community;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {
    public Page<Post> findAll(Pageable pageable);
}
