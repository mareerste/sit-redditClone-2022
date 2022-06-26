package rs.ac.uns.ftn.informatika.redditClone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.informatika.redditClone.model.dto.*;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.*;
import rs.ac.uns.ftn.informatika.redditClone.service.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/post")
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private CommunityService communityService;
    @Autowired
    private ReportService reportService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @Autowired
    private FlairService flairService;

    @GetMapping
    public ResponseEntity<List<PostDTO>>getPostsPage(Pageable pageable){
        Page<Post> posts = postService.findAll(pageable);
        List<PostDTO> postDTOList = new ArrayList<>();
        for (Post p: posts) {
            postDTOList.add(new PostDTO(p));
        }
        return new ResponseEntity<>(postDTOList, HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<PostDTO>> getAllPosts(){
        List<Post> posts = postService.findAll();
        List<PostDTO> postDTOList = new ArrayList<>();
        for (Post p: posts) {
            if(!communityService.findByPost(p.getId()).getSuspended() && reportService.findAllByPostAndAccepted(p) == 0)
                postDTOList.add(new PostDTO(p));
        }
        return new ResponseEntity<>(postDTOList, HttpStatus.OK);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<PostDTO> getPost(@PathVariable Integer id){
        Post post = postService.findOne(id);
        if(post == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(reportService.findAllByPostAndAccepted(post)!= 0)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(new PostDTO(post),HttpStatus.OK);
    }
    @GetMapping(value = "/{id}/community")
    public ResponseEntity<CommunityWithFlairsDTO> getPostCommunity(@PathVariable Integer id){
        Post post = postService.findOne(id);
        if(post == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Community community = communityService.findByPost(id);
        if(community == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new CommunityWithFlairsDTO(community),HttpStatus.OK);
    }

//    @GetMapping(value = "/{id}/comments")
//    public ResponseEntity<List<CommentDTO>> getPostComments(@PathVariable Integer id){
//        Post post = postService.findOne(id);
//        if(post == null) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        List<Comment> comments = commentService.findByPost(post);
//        List<CommentDTO> commentDTOList = new ArrayList<>();
//        for (Comment c: comments) {
//            commentDTOList.add(new CommentDTO(c));
//        }
//        return new ResponseEntity<>(commentDTOList,HttpStatus.OK);
//    }
    @PreAuthorize("hasAnyRole('USER','MODERATOR', 'ADMIN')")
    @PostMapping(consumes = "application/json")
    public ResponseEntity<PostDTO> savePost(@RequestBody PostCreateDTO postDTO){
        if(postDTO.getTitle().equals("")||postDTO.getTitle() == null || postDTO.getText().equals("")||postDTO.getText() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Post post = postService.save(postDTO);
        if(post == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(new PostDTO(post),HttpStatus.CREATED);
    }
    @PreAuthorize("hasAnyRole('USER','MODERATOR', 'ADMIN')")
    @PutMapping(consumes = "application/json")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostCreateDTO postDTO, Authentication authentication){
        Post post = postService.findOne(postDTO.getId());
        if (post == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        post.setTitle(postDTO.getTitle());
        post.setText(postDTO.getText());
        post.setImagePath(postDTO.getImagePath());
        post.setDeleted(postDTO.getDeleted());
//        post.setComments(postDTO.getComments());
        Set<Comment> comments = new HashSet<>();
        for (CommentDTO c:
             postDTO.getComments()) {
            comments.add(commentService.findOne(c.getId()));
        }
        post.setComments(comments);
        User user = userService.findOne(postDTO.getUser().getUsername());
        post.setUser(user);
        Flair flair = flairService.findOne(postDTO.getFlair().getId());
        post.setFlair(flair);

        post = postService.save(post);
        return new ResponseEntity<>(new PostDTO(post),HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('USER','MODERATOR', 'ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Integer id, Authentication authentication) {
        Post post = postService.findOne(id);

        if (post != null) {
            post.setDeleted(true);
            postService.save(post);
//            postService.delete(post);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
