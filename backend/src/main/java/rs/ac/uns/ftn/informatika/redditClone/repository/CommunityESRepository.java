package rs.ac.uns.ftn.informatika.redditClone.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.informatika.redditClone.model.dto.CommunityPostESDTO;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.CommunityES;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CommunityESRepository extends ElasticsearchRepository<CommunityES, Integer> {
    List<CommunityES> findAllByNameContaining(String name);
    List<CommunityES> findAllByDescriptionContaining(String description);
    List<CommunityES> findAllByRulesContaining(String rule);

    default void addPost (Integer communityId, CommunityPostESDTO post) throws IOException{
        Optional<CommunityES> optionalCommunity = findById(communityId);
        if (optionalCommunity.isPresent()) {
            CommunityES community = optionalCommunity.get();
            community.getPosts().add(post);
            save(community);
        } else {
            throw new IllegalArgumentException("Community with id " + communityId + " does not exist");
        }
    }

    @Query("{\"script\": {\"source\": \"ctx._source.posts.removeIf(post -> post.id == params.postId)\",\"lang\": \"painless\",\"params\": {\"postId\": ?1}}}")
    void removePost(Integer communityId, Integer postId);

    @Query("{\"bool\":{\"must\":[{\"range\":{\"posts\":{\"gte\":?0,\"lte\":?1}}}]}}")
    List<CommunityES> findAllByPostCountBetween(@Param("minPosts") Integer minPosts, @Param("maxPosts") Integer maxPosts);

    default List<CommunityES> findAllByPostCountGreaterThanEqual(Integer minPosts) {
        return findAllByPostCountBetween(minPosts, Integer.MAX_VALUE);
    }

    default List<CommunityES> findAllByPostCountLessThanEqual(Integer maxPosts) {
        return findAllByPostCountBetween(0, maxPosts);
    }
}
