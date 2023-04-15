package rs.ac.uns.ftn.informatika.redditClone.service.elasticsearch;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.informatika.redditClone.model.dto.CommunityPostESDTO;
import rs.ac.uns.ftn.informatika.redditClone.model.dto.CommunitySearchDTO;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Community;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.CommunityES;
import rs.ac.uns.ftn.informatika.redditClone.repository.CommunityESRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommunityServiceES {

    @Value("${files.path}")
    private String filepath;

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
}
