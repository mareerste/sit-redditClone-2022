package rs.ac.uns.ftn.informatika.redditClone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.informatika.redditClone.model.dto.CommentDTO;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Comment;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.User;
import rs.ac.uns.ftn.informatika.redditClone.service.CommentService;
import rs.ac.uns.ftn.informatika.redditClone.service.PostService;
import rs.ac.uns.ftn.informatika.redditClone.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;

    @PreAuthorize("hasAnyRole('USER','MODERATOR', 'ADMIN')")
    @GetMapping
    public ResponseEntity<List<CommentDTO>> getComments(){
        List<Comment> comments = commentService.findAll();
        List<CommentDTO> commentDTOList = new ArrayList<>();

        for (Comment c:comments) {
            commentDTOList.add(new CommentDTO(c));
        }
        return new ResponseEntity<>(commentDTOList,HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('USER','MODERATOR', 'ADMIN')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<CommentDTO>getComment(@PathVariable Integer id){
        Comment comment = commentService.findOne(id);
        if (comment == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(new CommentDTO(comment),HttpStatus.OK);
    }
//    @GetMapping(value = "/{id}/subcomments")
//    public ResponseEntity<List<CommentDTO>>getCommentChildrens(@PathVariable Integer id){
//        Comment comment = commentService.findOne(id);
//        if (comment == null)
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        List<Comment> childComments = commentService.findByParentComment(comment);
//        List<CommentDTO> commentDTOList = new ArrayList<>();
//        for ( Comment c: childComments) {
//            commentDTOList.add(new CommentDTO(c));
//        }
//        return new ResponseEntity<>(commentDTOList,HttpStatus.OK);
//    }
    @PreAuthorize("hasAnyRole('USER','MODERATOR', 'ADMIN')")
    @PostMapping(consumes = "application/json")
    public ResponseEntity<CommentDTO>saveComment(@RequestBody CommentDTO commentDTO){
        if (commentDTO.getText() == null || commentDTO.getText() == "")
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Comment comment = new Comment();
        comment.setText(commentDTO.getText());
        comment.setTimestamp(commentDTO.getTimestamp());
        comment.setDeleted(commentDTO.getDeleted());
        comment.setChildComments(commentDTO.getChildComments());
        User user = userService.findOne(commentDTO.getUser().getUsername());
        comment.setUser(user);
//        if (commentDTO.getPost() != null)
//            comment.setPost(postService.findOne(commentDTO.getPost().getId()));
        comment = commentService.save(comment);
        return new ResponseEntity<>(new CommentDTO(comment), HttpStatus.CREATED);
    }
    @PreAuthorize("hasAnyRole('USER','MODERATOR', 'ADMIN')")
    @PostMapping(value = "/{id}",consumes = "application/json")
    public ResponseEntity<CommentDTO>saveCommentWithParent(@PathVariable Integer id ,@RequestBody CommentDTO commentDTO){
        if (commentDTO.getText() == null || commentDTO.getText() == "")
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Comment parentComment = commentService.findOne(id);
        if (parentComment == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Comment comment = new Comment();
        comment.setText(commentDTO.getText());
        comment.setTimestamp(commentDTO.getTimestamp());
        comment.setChildComments(commentDTO.getChildComments());
        comment.setDeleted(commentDTO.getDeleted());
        User user = userService.findOne(commentDTO.getUser().getUsername());
        comment.setUser(user);
//        if (commentDTO.getPost() != null)
//            comment.setPost(postService.findOne(commentDTO.getPost().getId()));
        comment = commentService.save(comment);
        Set<Comment> commentsFromParent = parentComment.getChildComments();
        commentsFromParent.add(comment);
        parentComment = commentService.save(parentComment);
        return new ResponseEntity<>(new CommentDTO(comment), HttpStatus.CREATED);
    }
    @PreAuthorize("hasAnyRole('USER','MODERATOR', 'ADMIN')")
    @PutMapping(consumes = "application/json")
    public ResponseEntity<CommentDTO>updateComment(@RequestBody CommentDTO commentDTO){
        Comment comment = commentService.findOne(commentDTO.getId());
        if(comment == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        comment.setText(commentDTO.getText());
        comment.setTimestamp(commentDTO.getTimestamp());
        comment.setDeleted(commentDTO.getDeleted());
        comment.setChildComments(commentDTO.getChildComments());
        User user = userService.findOne(commentDTO.getUser().getUsername());
        comment.setUser(user);

//        if (commentDTO.getPost() != null)
//            comment.setPost(postService.findOne(commentDTO.getPost().getId()));
        comment = commentService.save(comment);
        return new ResponseEntity<>(new CommentDTO(comment), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('USER','MODERATOR', 'ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void>deleteComment(@PathVariable Integer id){
        Comment comment = commentService.findOne(id);
        if (comment != null) {
            commentService.delete(comment);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
