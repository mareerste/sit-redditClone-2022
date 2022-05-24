package rs.ac.uns.ftn.informatika.redditClone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.informatika.redditClone.model.dto.*;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Comment;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Post;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Reaction;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.User;
import rs.ac.uns.ftn.informatika.redditClone.model.enumerations.ReactionType;
import rs.ac.uns.ftn.informatika.redditClone.service.CommentService;
import rs.ac.uns.ftn.informatika.redditClone.service.PostService;
import rs.ac.uns.ftn.informatika.redditClone.service.ReactionService;
import rs.ac.uns.ftn.informatika.redditClone.service.UserService;

import java.util.ArrayList;
import java.util.List;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/reaction")
public class ReactionController {
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private ReactionService reactionService;
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<ReactionDTO>>getReactions(){
        List<Reaction> reactions = reactionService.findAll();
        List<ReactionDTO> reactionDTOList = new ArrayList<>();
        for (Reaction r:reactions) {
            reactionDTOList.add(new ReactionDTO(r));
        }
        return new ResponseEntity<>(reactionDTOList, HttpStatus.OK);
    }
    @GetMapping(value = "/comment")
    public ResponseEntity<List<ReactionCommentDTO>>getReactionsForComments(){
        List<Reaction> reactions = reactionService.findAllForComments();
        List<ReactionCommentDTO> reactionCommentDTOList = new ArrayList<>();
        for (Reaction r: reactions) {
            reactionCommentDTOList.add(new ReactionCommentDTO(r));
        }
        return new ResponseEntity<>(reactionCommentDTOList, HttpStatus.OK);
    }
    @GetMapping(value = "/post")
    public ResponseEntity<List<ReactionPostDTO>>getReactionsForPosts(){
        List<Reaction> reactions = reactionService.findAllForPosts();
        List<ReactionPostDTO> reactionPostDTOList = new ArrayList<>();
        for (Reaction r: reactions) {
            reactionPostDTOList.add(new ReactionPostDTO(r));
        }
        return new ResponseEntity<>(reactionPostDTOList, HttpStatus.OK);
    }
    @GetMapping(value = "/post/{id}")
    public ResponseEntity<List<ReactionPostDTO>>getPostReactions(@PathVariable Integer id){
        Post post = postService.findOne(id);
        List<Reaction> reactions = reactionService.findAllByPost(post);
        List<ReactionPostDTO> reactionPostDTOList = new ArrayList<>();
        for (Reaction r:reactions) {
            reactionPostDTOList.add(new ReactionPostDTO(r));
        }
        return new ResponseEntity<>(reactionPostDTOList, HttpStatus.OK);
    }

    @GetMapping(value = "/post/{id}/karma")
    public ResponseEntity<Integer>getPostReactionsKarma(@PathVariable Integer id){
        Post post = postService.findOne(id);
        List<Reaction> reactions = reactionService.findAllByPost(post);
        int result = 0;
        for (Reaction r:reactions) {
            if(r.getType() == ReactionType.UPVOTE)
                result ++;
            else
                result --;
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping(value = "/comment/{id}")
    public ResponseEntity<List<ReactionCommentDTO>>getCommentReactions(@PathVariable Integer id){
        Comment comment = commentService.findOne(id);
        List<Reaction> reactions = reactionService.findAllByComment(comment);
        List<ReactionCommentDTO> reactionCommentDTOList = new ArrayList<>();
        for (Reaction r:reactions) {
            reactionCommentDTOList.add(new ReactionCommentDTO(r));
        }
        return new ResponseEntity<>(reactionCommentDTOList, HttpStatus.OK);
    }
    @GetMapping(value = "/comment/{id}/karma")
    public ResponseEntity<Integer>getCommentReactionsKarma(@PathVariable Integer id){
        Comment comment = commentService.findOne(id);
        List<Reaction> reactions = reactionService.findAllByComment(comment);
        int result = 0;
        for (Reaction r:reactions) {
            if(r.getType() == ReactionType.UPVOTE)
                result ++;
            else
                result --;
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('USER','MODERATOR', 'ADMIN')")
    @PostMapping(consumes = "application/json")
    public ResponseEntity<ReactionDTO> saveReaction(@RequestBody ReactionDTO reactionDTO){
        if(reactionDTO.getComment() == null && reactionDTO.getPost() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        List<Reaction> reactionExist = reactionService.findByPostAndUser(postService.findOne(reactionDTO.getPost().getId()), userService.findOne(reactionDTO.getUser().getUsername()));
        if(!reactionExist.isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Reaction reaction = new Reaction();
        reaction.setType(reactionDTO.getType());
        reaction.setTimestamp(reactionDTO.getTimestamp());
        reaction.setUser(userService.findOne(reactionDTO.getUser().getUsername()));
        reaction.setComment(commentService.findOne(reactionDTO.getComment().getId()));
        reaction.setPost(postService.findOne(reactionDTO.getPost().getId()));
        reaction = reactionService.save(reaction);

        return new ResponseEntity<>(new ReactionDTO(reaction),HttpStatus.CREATED);
    }
    @PreAuthorize("hasAnyRole('USER','MODERATOR', 'ADMIN')")
    @PutMapping(consumes = "application/json")
    public ResponseEntity<ReactionDTO> updateReaction(@RequestBody ReactionDTO reactionDTO){
        Reaction reaction = reactionService.findOne(reactionDTO.getId());
        if(reaction == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        reaction.setType(reactionDTO.getType());
        reaction.setTimestamp(reactionDTO.getTimestamp());
        reaction.setUser(userService.findOne(reactionDTO.getUser().getUsername()));
        reaction = reactionService.save(reaction);

        return new ResponseEntity<>(new ReactionDTO(reaction),HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('USER','MODERATOR', 'ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteReaction(@PathVariable Integer id){
        Reaction reaction = reactionService.findOne(id);
        if(reaction != null){
            reactionService.delete(reaction);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
