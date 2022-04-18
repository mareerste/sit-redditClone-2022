package rs.ac.uns.ftn.informatika.redditClone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.informatika.redditClone.model.dto.CommunityDTO;
import rs.ac.uns.ftn.informatika.redditClone.model.dto.CommunityWithFlairsDTO;
import rs.ac.uns.ftn.informatika.redditClone.model.dto.PostDTO;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.*;
import rs.ac.uns.ftn.informatika.redditClone.service.CommunityService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/community")
public class CommunityController {
    @Autowired
    private CommunityService communityService;

    @GetMapping
    public ResponseEntity<List<CommunityDTO>> getCommunities(){
        List<Community> communities = communityService.findAll();
        List<CommunityDTO> communityDTOList = new ArrayList<>();
        for (Community com :communities){
            communityDTOList.add(new CommunityDTO(com));
        }

        return new ResponseEntity<>(communityDTOList, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CommunityDTO> getCommunity(@PathVariable Integer id) {

        Community community = communityService.findOne(id);

        if (community == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new CommunityDTO(community), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<CommunityWithFlairsDTO> saveCommunity(@RequestBody CommunityWithFlairsDTO communityDTO) {

        if(communityDTO.getName() == null || communityDTO.getModerators().isEmpty() || communityDTO.getDescription() == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Community community = new Community();
        community.setName(communityDTO.getName());
        community.setDescription(communityDTO.getDescription());
        community.setCreationDate(communityDTO.getCreationDate());
        community.setRules(communityDTO.getRules());
        community.setSuspended(communityDTO.getSuspended());
        community.setSuspendedReason(communityDTO.getSuspendedReason());
        community.setModerators(communityDTO.getModerators());
        community.setFlairs(communityDTO.getFlairs());

        community = communityService.save(community);
        return new ResponseEntity<>(new CommunityWithFlairsDTO(community), HttpStatus.CREATED);
    }

    @PutMapping(consumes = "application/json")
    public ResponseEntity<CommunityDTO> updateCommunity(@RequestBody CommunityDTO communityDTO) {

        Community community = communityService.findOne(communityDTO.getId());

        if (community == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        community.setName(communityDTO.getName());
        community.setDescription(communityDTO.getDescription());
        community.setCreationDate(communityDTO.getCreationDate());
        community.setRules(communityDTO.getRules());
        community.setSuspended(communityDTO.getSuspended());
        community.setSuspendedReason(communityDTO.getSuspendedReason());

        community = communityService.save(community);
        return new ResponseEntity<>(new CommunityDTO(community), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCommunity(@PathVariable Integer id) {

        Community community = communityService.findOne(id);

        if (community != null) {
            communityService.delete(community);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{communityId}/posts")
    public ResponseEntity<List<PostDTO>> getCommunityPosts(@PathVariable Integer communityId) {
        Community community = communityService.findOne(communityId);

        Set<Post> posts = community.getPosts();
        List<PostDTO> postsDTO = new ArrayList<>();

        for (Post e : posts) {
            PostDTO postDTO = new PostDTO(e);
            postsDTO.add(postDTO);
        }

        return new ResponseEntity<>(postsDTO, HttpStatus.OK);
    }
}
