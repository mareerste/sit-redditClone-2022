package rs.ac.uns.ftn.informatika.redditClone.repository;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.CommunityES;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.PostES;

import java.util.List;

@Repository
public interface PostESRepository extends ElasticsearchRepository<PostES, Integer> {
    List<PostES> findAllByTitle(String title);
    List<PostES> findAllByFlair(String flair);
    @Query("{\"bool\": {\"should\": [{\"match\": {\"text\": \"?0\"}}, {\"match\": {\"textFile\": \"?0\"}}]}}")
    List<PostES> findAllByTextContainingOrTextFileContaining(String searchTerm);
    @Query("{\"range\" : {\"karma\" : {\"gte\" : ?0, \"lte\" : ?1}}}")
    List<PostES> findByKarmaInRange(Integer minKarma, Integer maxKarma);
    @Query("{\"range\" : {\"comments\" : {\"gte\" : ?0, \"lte\" : ?1}}}")
    List<PostES> findByCommCountInRange(Integer minComm, Integer maxComm);

}
