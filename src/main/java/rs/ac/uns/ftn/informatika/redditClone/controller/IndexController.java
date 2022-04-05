package rs.ac.uns.ftn.informatika.redditClone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import rs.ac.uns.ftn.informatika.redditClone.model.*;
import rs.ac.uns.ftn.informatika.redditClone.model.enumerations.ReactionType;
import rs.ac.uns.ftn.informatika.redditClone.service.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Controller
public class IndexController {
    @Autowired
    private AdministratorService administratorService;
    @Autowired
    private BannedService bannedService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private CommunityService communityService;
    @Autowired
    private FlairService flairService;
    @Autowired
    private ModeratorService moderatorService;
    @Autowired
    private PostService postService;
    @Autowired
    private ReactionService reactionService;
    @Autowired
    private ReportService reportService;
    @Autowired
    private UserService userService;

    @GetMapping("/")
    @ResponseBody
    public String index(){
//        Moderator moderator1 = moderatorService.findOne("mirko123");
//        Moderator moderator2 = moderatorService.findOne("pera123");
//        System.out.println(moderator1);
//        System.out.println(moderator2);

//        User user = userService.findOne("marko123");
//        Post post = postService.findOne(1);
//        System.out.println(post.getUser());

//        Community community = communityService.findOne(1);
//        System.out.println(community);
        System.out.println(userService.findAll());
        System.out.println(moderatorService.findAll());
        System.out.println(administratorService.findAll());
        System.out.println(communityService.findAll());
        System.out.println(postService.findAll());
        System.out.println(flairService.findAll());
        System.out.println(commentService.findAll());
        System.out.println(commentService.findByPost(postService.findOne(1)));
        System.out.println(reactionService.findAll());

        return "<h2>Hello World</h2>";
    }
}
