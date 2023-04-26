package rs.ac.uns.ftn.informatika.redditClone.repository;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.informatika.redditClone.model.dto.CommunityPostESDTO;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.CommunityES;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.PostES;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public interface CommunityESRepository extends ElasticsearchRepository<CommunityES, Integer> {

    List<CommunityES> findAllByNameContaining(String name);
//    List<CommunityES> findAllByDescriptionContaining(String description);
    @Query("{\"bool\": {\"should\": [{\"match\": {\"description\": \"?0\"}}, {\"match\": {\"descriptionFile\": \"?0\"}}]}}")
    List<CommunityES> findAllByDescriptionContainingOrDescriptionFileContaining(String searchTerm);
    List<CommunityES> findAllByRulesContaining(String rule);
    @Query("{\"bool\": {\"must\": [{\"nested\": {\"path\": \"posts\", \"query\": {\"match\": {\"posts.id\": \"?0\"}}}}]}}")
    List<CommunityES> findByPostId(Integer postId);


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
    @Query("{\"bool\":{\"filter\":{\"script\":{\"script\":\"doc['posts.id'].size() >= ?0 && doc['posts.id'].size() < ?1\"}}}}")
    List<CommunityES> findByPostRange(Integer minPostId, Integer maxPostId);

    @Query("{\"range\" : {\"karma\" : {\"gte\" : ?0, \"lte\" : ?1}}}")
    List<CommunityES> findByKarmaInRange(Integer minKarma, Integer maxKarma);

}
