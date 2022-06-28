package rs.ac.uns.ftn.informatika.redditClone.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/reaction")
public class ReactionController {
    Logger logger = LoggerFactory.getLogger(ReactionController.class);
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
//    @GetMapping(value = "/post/{id}")
//    public ResponseEntity<List<ReactionPostDTO>>getPostReactions(@PathVariable Integer id){
//        Post post = postService.findOne(id);
//        List<Reaction> reactions = reactionService.findAllByPost(post);
//        List<ReactionPostDTO> reactionPostDTOList = new ArrayList<>();
//        for (Reaction r:reactions) {
//            reactionPostDTOList.add(new ReactionPostDTO(r));
//        }
//        return new ResponseEntity<>(reactionPostDTOList, HttpStatus.OK);
//    }

    @GetMapping(value = "/post/{id}")
    public ResponseEntity<Integer>getPostReactions(@PathVariable Integer id){
        Post post = postService.findOne(id);
        List<Reaction> reactions = reactionService.findAllByPost(post);
        List<ReactionPostDTO> reactionPostDTOList = new ArrayList<>();
        for (Reaction r:reactions) {
            reactionPostDTOList.add(new ReactionPostDTO(r));
        }
        return new ResponseEntity<>(reactions.size(), HttpStatus.OK);
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
    public ResponseEntity<ReactionDTO> saveReaction(@RequestBody ReactionDTO reactionDTO, Authentication authentication){
        if(reactionDTO.getComment() == null && reactionDTO.getPost() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if(reactionDTO.getPost() != null) {
            List<Reaction> reactionExistPost = reactionService.findByPostAndUser(postService.findOne(reactionDTO.getPost()), userService.findOne(authentication.getName()));
            if (!reactionExistPost.isEmpty()) {
                if (reactionExistPost.get(0).getType() == reactionDTO.getType())
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                else {
                    Reaction reactionUpdate = reactionService.findOne(reactionExistPost.get(0).getId());
                    reactionUpdate.setType(reactionDTO.getType());
                    Reaction result = reactionService.save(reactionUpdate);

                    return new ResponseEntity<>(new ReactionDTO(result), HttpStatus.OK);
                }
            }
        }
        if(reactionDTO.getComment() != null) {
            List<Reaction> reactionExistComment = reactionService.findByCommentAndUser(commentService.findOne(reactionDTO.getComment()), userService.findOne(authentication.getName()));
            if (!reactionExistComment.isEmpty())
                if(reactionExistComment.get(0).getType() == reactionDTO.getType())
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                else{
                    Reaction reactionUpdate = reactionService.findOne(reactionExistComment.get(0).getId());
                    reactionUpdate.setType(reactionDTO.getType());
                    Reaction result = reactionService.save(reactionUpdate);
                    return new ResponseEntity<>(new ReactionDTO(result),HttpStatus.OK);
                }
        }
        Reaction reaction = new Reaction();
        reaction.setType(reactionDTO.getType());
        reaction.setUser(userService.findOne(authentication.getName()));
        if(reactionDTO.getComment() != null)
            reaction.setComment(commentService.findOne(reactionDTO.getComment()));
        if(reactionDTO.getPost() != null)
            reaction.setPost(postService.findOne(reactionDTO.getPost()));
        reaction = reactionService.save(reaction);
        logger.info("Reaction " +reaction.getId()+ " created ");
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
        logger.info("Reaction " +reaction.getId()+ " updated " + LocalDate.now().toString());
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
