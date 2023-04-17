package rs.ac.uns.ftn.informatika.redditClone.service.elasticsearch;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.ScriptType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.informatika.redditClone.model.dto.CommunityPostESDTO;
import rs.ac.uns.ftn.informatika.redditClone.model.dto.CommunitySearchDTO;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Community;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.CommunityES;
import rs.ac.uns.ftn.informatika.redditClone.repository.CommunityESRepository;
import org.elasticsearch.script.Script;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommunityServiceES {

    @Value("${files.path}")
    private String filepath;

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    private final CommunityESRepository communityESRepository;
    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    public CommunityServiceES(CommunityESRepository communityESRepository, ElasticsearchRestTemplate elasticsearchRestTemplate){
        this.communityESRepository = communityESRepository;
        this.elasticsearchRestTemplate = elasticsearchRestTemplate;
    }

    public void index(Community community){communityESRepository.save(new CommunityES(community));}
    public void index(CommunityES community){communityESRepository.save(community);}

    public CommunityES findCommunityById(Integer id){

        Optional <CommunityES> communityES = communityESRepository.findById(id);
        if (communityES.isPresent()){
            return communityES.get();
        }else{
            return null;
        }
    }

    public List<CommunitySearchDTO> findCommunitiesByName(String name){
        List<CommunityES> communities = communityESRepository.findAllByNameContaining(name);
        return mapCommunityESToCommunitySearchDTO(communities);
    }

    public List<CommunitySearchDTO> findCommunitiesByDescription(String description){
        List<CommunityES> communities = communityESRepository.findAllByDescriptionContaining(description);
        return mapCommunityESToCommunitySearchDTO(communities);
    }

    public List<CommunitySearchDTO> findCommunitiesByRules(String rule){
        List<CommunityES> communities = communityESRepository.findAllByRulesContaining(rule);
        return mapCommunityESToCommunitySearchDTO(communities);
    }

    public void addPostToCommunity(Integer communityId, CommunityPostESDTO post) throws IOException {
        communityESRepository.addPost(communityId, post);
    }

    public List<CommunitySearchDTO> findByPostRangeBetween(Integer min, Integer max){
        return mapCommunityESToCommunitySearchDTO(communityESRepository.findByPostRange(min, max));
    }

    private List<CommunitySearchDTO> mapCommunityESToCommunitySearchDTO(List<CommunityES> communities){
        List<CommunitySearchDTO> communitiesDTO = new ArrayList<>();
        for(CommunityES community : communities){
            communitiesDTO.add(new CommunitySearchDTO(community));
        }
        return communitiesDTO;
    }

    public List<CommunitySearchDTO> searchFuzzyCommunities(String name, String description, String rule, Integer minPost, Integer maxPost) {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.fuzzyQuery("name", name))
                .must(QueryBuilders.fuzzyQuery ("description", description))
                .must(QueryBuilders.fuzzyQuery("rules", rule));
        if (minPost != null && maxPost != null) {
            List<CommunityES> communitiesWithPostRange = communityESRepository.findByPostRange(minPost, maxPost);
            queryBuilder.filter(QueryBuilders.termsQuery("id", communitiesWithPostRange.stream().map(CommunityES::getId).toArray(Integer[]::new)));
        }

        Query query = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .build();

        if (elasticsearchOperations == null) {
            throw new NullPointerException("elasticsearchOperations is null");
        }

        SearchHits<CommunityES> searchHits = elasticsearchOperations.search(
                query,
                CommunityES.class
        );

        List<CommunityES> retVal = searchHits.get().map(SearchHit::getContent).collect(Collectors.toList());
        return mapCommunityESToCommunitySearchDTO(retVal);
    }

    public List<CommunitySearchDTO> searchPhraseCommunities(String name, String description, String rule, Integer minPost, Integer maxPost) {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.matchPhraseQuery("name", name))
                .must(QueryBuilders.matchPhraseQuery ("description", description))
                .must(QueryBuilders.matchPhraseQuery("rules", rule));
        if (minPost != null && maxPost != null) {
            List<CommunityES> communitiesWithPostRange = communityESRepository.findByPostRange(minPost, maxPost);
            queryBuilder.filter(QueryBuilders.termsQuery("id", communitiesWithPostRange.stream().map(CommunityES::getId).toArray(Integer[]::new)));
        }

        Query query = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .build();

        if (elasticsearchOperations == null) {
            throw new NullPointerException("elasticsearchOperations is null");
        }

        SearchHits<CommunityES> searchHits = elasticsearchOperations.search(
                query,
                CommunityES.class
        );

        List<CommunityES> retVal = searchHits.get().map(SearchHit::getContent).collect(Collectors.toList());
        return mapCommunityESToCommunitySearchDTO(retVal);
    }
}
