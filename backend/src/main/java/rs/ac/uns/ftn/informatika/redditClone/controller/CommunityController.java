package rs.ac.uns.ftn.informatika.redditClone.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Banned;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Community;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Moderator;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.User;

@RestController
@RequestMapping(value = "/community")
public class CommunityController {

    @ResponseBody
    @GetMapping
    public String index(){
//
        return "<h2>Hello World from community controller</h2>";
    }
}
