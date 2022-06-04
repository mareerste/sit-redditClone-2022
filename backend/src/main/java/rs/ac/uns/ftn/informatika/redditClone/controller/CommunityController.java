package rs.ac.uns.ftn.informatika.redditClone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.informatika.redditClone.model.dto.*;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.*;
import rs.ac.uns.ftn.informatika.redditClone.service.*;

import java.time.LocalDate;
import java.util.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/community")
public class CommunityController {
    @Autowired
    private CommunityService communityService;
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @Autowired
    private ModeratorService moderatorService;
    @Autowired
    private ReactionService reactionService;

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
    public ResponseEntity<CommunityWithFlairsDTO> getCommunity(@PathVariable Integer id) {

        Community community = communityService.findOne(id);

        if (community == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new CommunityWithFlairsDTO(community), HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('USER','MODERATOR', 'ADMIN')")
    @PostMapping(consumes = "application/json")
    public ResponseEntity<CommunityWithFlairsDTO> saveCommunity(@RequestBody CommunityWithFlairsDTO communityDTO) {

        if(communityDTO.getName() == null || communityDTO.getModerators().isEmpty() || communityDTO.getDescription() == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Community community = new Community();
        community.setName(communityDTO.getName());
        community.setDescription(communityDTO.getDescription());
        community.setCreationDate(LocalDate.now());
        community.setRules(communityDTO.getRules());
        community.setSuspended(communityDTO.getSuspended());
        community.setSuspendedReason(communityDTO.getSuspendedReason());
        community.setModerators(communityDTO.getModerators());
        community.setFlairs(communityDTO.getFlairs());
        community = communityService.save(community);
        User user = community.getModerators().iterator().next();
        setModerator(user.getUsername());
        return new ResponseEntity<>(new CommunityWithFlairsDTO(community), HttpStatus.CREATED);
    }

    public Moderator setModerator(String username){
        User user = userService.findOne(username);
        if (user == null)
            return moderatorService.findOne(username);
        Moderator moderator = new Moderator();
        moderator.setUsername(user.getUsername());
        moderator.setPassword(user.getPassword());
        moderator.setEmail(user.getEmail());
        moderator.setAvatar(user.getAvatar());
        moderator.setRegistrationDate(user.getRegistrationDate());
        moderator.setDescription(user.getDescription());
        userService.delete(user.getUsername());
        moderatorService.save(moderator);

        return moderator;
    }
    @PreAuthorize("hasAnyRole('MODERATOR', 'ADMIN')")
    @PutMapping(consumes = "application/json")
    public ResponseEntity<CommunityWithFlairsDTO> updateCommunity(@RequestBody CommunityWithFlairsDTO communityDTO) {

        Community community = communityService.findOne(communityDTO.getId());

        if (community == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        community.setName(communityDTO.getName());
        community.setDescription(communityDTO.getDescription());
        community.setRules(communityDTO.getRules());
        community.setFlairs(communityDTO.getFlairs());
        community.setSuspended(communityDTO.getSuspended());
        community.setSuspendedReason(communityDTO.getSuspendedReason());

        community = communityService.save(community);
        return new ResponseEntity<>(new CommunityWithFlairsDTO(community), HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('MODERATOR', 'ADMIN')")
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

    @GetMapping(value = "/{id}/posts")
    public ResponseEntity<List<PostDTO>> getCommunityPosts(@PathVariable Integer id) {
        Community community = communityService.findOne(id);

        Set<Post> posts = community.getPosts();
        List<PostDTO> postsDTO = new ArrayList<>();

        for (Post e : posts) {
            PostDTO postDTO = new PostDTO(e);
            postsDTO.add(postDTO);
        }

        return new ResponseEntity<>(postsDTO, HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('USER','MODERATOR', 'ADMIN')")
    @PostMapping(value = "/{id}/posts")
    public ResponseEntity<PostDTO> createPost(@PathVariable Integer id, @RequestBody PostCreateDTO postDTO, Authentication authentication) {

        Community community = communityService.findOne(id);
        if (community == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(postDTO.getTitle().equals("")||postDTO.getTitle() == null || postDTO.getText().equals("")||postDTO.getText() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        postDTO.setUser(new UserCreateDTO(userService.findOne(authentication.getName())));
        Post post = postService.save(postDTO);
        if(post == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        Reaction reaction = new Reaction(userService.findOne(authentication.getName()),post);
        reactionService.save(reaction);

        Set<Post> posts = community.getPosts();
        posts.add(post);
        community.setPosts(posts);
        communityService.save(community);

        return new ResponseEntity<>(new PostDTO(post),HttpStatus.CREATED);
    }
    @PreAuthorize("hasAnyRole('USER','MODERATOR', 'ADMIN')")
    @DeleteMapping(value = "/{id}/posts/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Integer id,@PathVariable Integer postId) {

        Community community = communityService.findOne(id);
        if (community == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Post post = postService.findOne(postId);
        if (post == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            Set<Post> posts = community.getPosts();
            Set<Post> newPosts = new HashSet<>();
            for (Post p:posts) {
                if (p.getId() != post.getId())
                    newPosts.add(p);
            }
            community.setPosts(newPosts);
            communityService.save(community);
            postService.delete(post);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PreAuthorize("hasAnyRole('USER','MODERATOR', 'ADMIN')")
    @GetMapping(value = "/{id}/posts/{postId}")
    public ResponseEntity<PostDTO> getPost(@PathVariable Integer id,@PathVariable Integer postId) {

        Community community = communityService.findOne(id);
        if (community == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Post post = postService.findOne(postId);
        if (post == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(new PostDTO(post),HttpStatus.OK);
        }
    }
}
