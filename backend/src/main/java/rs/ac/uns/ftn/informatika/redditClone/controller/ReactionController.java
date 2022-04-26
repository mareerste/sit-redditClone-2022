package rs.ac.uns.ftn.informatika.redditClone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.informatika.redditClone.model.dto.*;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Comment;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Post;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Reaction;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.User;
import rs.ac.uns.ftn.informatika.redditClone.service.CommentService;
import rs.ac.uns.ftn.informatika.redditClone.service.PostService;
import rs.ac.uns.ftn.informatika.redditClone.service.ReactionService;
import rs.ac.uns.ftn.informatika.redditClone.service.UserService;

import java.util.ArrayList;
import java.util.List;

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

    @PostMapping(consumes = "application/json")
    public ResponseEntity<ReactionDTO> saveUser(@RequestBody ReactionDTO reactionDTO){
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

    @PutMapping(consumes = "application/json")
    public ResponseEntity<ReactionDTO> updateUser(@RequestBody ReactionDTO reactionDTO){
        Reaction reaction = reactionService.findOne(reactionDTO.getId());
        if(reaction == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        reaction.setType(reactionDTO.getType());
        reaction.setTimestamp(reactionDTO.getTimestamp());
        reaction.setUser(userService.findOne(reactionDTO.getUser().getUsername()));
        reaction = reactionService.save(reaction);

        return new ResponseEntity<>(new ReactionDTO(reaction),HttpStatus.OK);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id){
        Reaction reaction = reactionService.findOne(id);
        if(reaction != null){
            reactionService.delete(reaction);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
