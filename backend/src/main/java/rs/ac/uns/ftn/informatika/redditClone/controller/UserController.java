package rs.ac.uns.ftn.informatika.redditClone.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.informatika.redditClone.model.dto.*;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Comment;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Community;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Post;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.User;
import rs.ac.uns.ftn.informatika.redditClone.security.TokenUtils;
import rs.ac.uns.ftn.informatika.redditClone.service.*;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/user")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private CommunityService communityService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private ReactionService reactionService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private PostService postService;
    @Autowired
    TokenUtils tokenUtils;

    @GetMapping
    public ResponseEntity<List<UserDTO>>getUsers(){
        List<User> users = userService.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User u: users) {
            userDTOList.add(new UserDTO(u));
        }

        return new ResponseEntity<>(userDTOList, HttpStatus.OK);
    }
    @GetMapping(value = "/{username}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String username){
        User user = userService.findOne(username);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('USER','MODERATOR', 'ADMIN')")
    @GetMapping(value = "/{username}/posts")
    public ResponseEntity<List<PostDTO>> getUserPosts(@PathVariable String username){
        User user = userService.findOne(username);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        List<Post> posts = postService.findAllByUser(user);
        List<PostDTO> usersPosts = new ArrayList<>();
        for (Post p: posts) {
            usersPosts.add(new PostDTO(p));
        }
        return new ResponseEntity<>(usersPosts, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('USER','MODERATOR', 'ADMIN')")
    @GetMapping(value = "/{username}/comments")
    public ResponseEntity<List<CommentDTO>> getUserComments(@PathVariable String username){
        User user = userService.findOne(username);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        List<Comment> comments = commentService.findAllByUser(user);
        List<CommentDTO> usersComments = new ArrayList<>();
        for (Comment c: comments) {
            usersComments.add(new CommentDTO(c));
        }
        return new ResponseEntity<>(usersComments, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('USER','MODERATOR', 'ADMIN')")
    @GetMapping(value = "/{username}/karma")
    public ResponseEntity<Long> getUsersKarm(@PathVariable String username){
        User user = userService.findOne(username);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Long karma = 0L;
        Long upvotes = reactionService.getUsersUpvotes(user);
        Long downvotes = reactionService.getUsersDownvotes(user);
        karma = upvotes - downvotes;
        return new ResponseEntity<>(karma, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserCreateDTO newUserDTO){
        if(newUserDTO.getUsername().equals("")||newUserDTO.getUsername() == null || newUserDTO.getPassword().equals("")||newUserDTO.getPassword() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        User user = userService.save(newUserDTO);
        if(user == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        logger.info("User " +user.getUsername()+ " created ");
        return new ResponseEntity<>(new UserDTO(user),HttpStatus.CREATED);
    }
    @PreAuthorize("hasAnyRole('USER','MODERATOR', 'ADMIN')")
    @PutMapping(consumes = "application/json")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserCreateDTO userCreateDTO){
        if(userCreateDTO.getUsername().equals("")||userCreateDTO.getUsername() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        User user = userService.findOne(userCreateDTO.getUsername());
        if(user == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//
        if(userCreateDTO.getPassword() != null)
            user.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));
        user.setEmail(userCreateDTO.getEmail());
        user.setAvatar(userCreateDTO.getAvatar());
        user.setRegistrationDate(userCreateDTO.getRegistrationDate());
        user.setDescription(userCreateDTO.getDescription());
        user.setDisplayName(userCreateDTO.getDisplayName());
        user = userService.save(user);
        logger.info("User " +user.getUsername()+ " updated ");
        return new ResponseEntity<>(new UserDTO(user),HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('USER','MODERATOR', 'ADMIN')")
    @DeleteMapping(value = "/delete/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username){
        User user = userService.findOne(username);
        if(user != null){
            userService.delete(user.getUsername());
            logger.info("User deleted");
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            logger.error("User not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PreAuthorize("hasAnyRole('USER','MODERATOR', 'ADMIN')")
    @DeleteMapping(value = "/{username}")
    public ResponseEntity<UserDTO> deleteUserLogic(@PathVariable String username){
        User user = userService.findOne(username);
        if(user != null){
            user.setDeleted(true);
            userService.save(user);
            logger.info("User " +user.getUsername()+ " deleted");
            return new ResponseEntity<>(new UserDTO(user),HttpStatus.OK);
        }else{
            logger.error("User not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @PostMapping(value = "/login")
//    public ResponseEntity<UserDTO>login(@RequestParam String username, @RequestParam String password){
//        User user = userService.login(username,password);
//        if(user == null)
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        return new ResponseEntity<>(new UserDTO(user),HttpStatus.OK);
//    }
    @PostMapping("/login")
    public ResponseEntity<UserTokenState> createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletResponse response) {

        // Ukoliko kredencijali nisu ispravni, logovanje nece biti uspesno, desice se
        // AuthenticationException
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(), authenticationRequest.getPassword()));

        // Ukoliko je autentifikacija uspesna, ubaci korisnika u trenutni security
        // kontekst
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String role = authentication.getAuthorities().toString();
        role = role.substring(1, role.length()-1);
        // Kreiraj token za tog korisnika
        UserDetails user = (UserDetails) authentication.getPrincipal();
        String jwt = tokenUtils.generateToken(user);
        int expiresIn = tokenUtils.getExpiredIn();
//        String role = use
        // Vrati token kao odgovor na uspesnu autentifikaciju
        logger.info("Login method called");
        return ResponseEntity.ok(new UserTokenState(jwt, expiresIn,user.getUsername(),role));
    }
    @PreAuthorize("hasAnyRole('USER','MODERATOR', 'ADMIN')")
    @PutMapping(value = "/changePassword")
    public ResponseEntity<UserDTO> changePassword(@RequestParam String username,@RequestParam String oldPassword, @RequestParam String newPassword,@RequestParam String newPasswordRepeat){
        User user = userService.findOne(username);
        if(user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        oldPassword = passwordEncoder.encode(oldPassword);

//        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
//                .getPrincipal();
//        String password = userDetails.getPassword();

//        if(!user.getPassword().equals(oldPassword))
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if(!newPassword.equals(newPasswordRepeat))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        user.setPassword(passwordEncoder.encode(newPassword));
        user = userService.save(user);
        return new ResponseEntity<>(new UserDTO(user),HttpStatus.OK);
    }

    @GetMapping("/whoami")
    @PreAuthorize("hasAnyRole('USER','MODERATOR', 'ADMIN')")
    public User user(Principal user) {
        return this.userService.findOne(user.getName());
    }

    @GetMapping(value = "/{username}/communities")
    @PreAuthorize("hasAnyRole('USER','MODERATOR', 'ADMIN')")
    public ResponseEntity<List<CommunityWithFlairsDTO>> getCommunities(@PathVariable String username){

        User user = userService.findOne(username);
        if(user != null){
            List<Community> communities = communityService.findAllByUser(user);
            List<CommunityWithFlairsDTO> communityDTOList = new ArrayList<>();
            for (Community com :communities){
                communityDTOList.add(new CommunityWithFlairsDTO(com));
            }
            return new ResponseEntity<>(communityDTOList, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
//PROJECT FINISHED

}
