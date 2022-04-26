package rs.ac.uns.ftn.informatika.redditClone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.informatika.redditClone.model.dto.ModeratorCreateDTO;
import rs.ac.uns.ftn.informatika.redditClone.model.dto.ModeratorDTO;
import rs.ac.uns.ftn.informatika.redditClone.model.dto.UserCreateDTO;
import rs.ac.uns.ftn.informatika.redditClone.model.dto.UserDTO;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Moderator;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.User;
import rs.ac.uns.ftn.informatika.redditClone.service.ModeratorService;
import rs.ac.uns.ftn.informatika.redditClone.service.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "moderator")
public class ModeratorController {
    @Autowired
    private ModeratorService moderatorService;
    @Autowired
    private UserService userService;
    @GetMapping
    public ResponseEntity<List<ModeratorDTO>>getModerators(){
        List<Moderator> moderators = moderatorService.findAll();
        List<ModeratorDTO> moderatorDTOList = new ArrayList<>();
        for (Moderator m: moderators) {
            moderatorDTOList.add(new ModeratorDTO(m));
        }
        return new ResponseEntity<>(moderatorDTOList, HttpStatus.OK);
    }
    @PostMapping(consumes = "application/json")
    public ResponseEntity<ModeratorDTO>saveModerator(@RequestBody ModeratorCreateDTO moderatorCreateDTO){
        Moderator moderator = new Moderator();
        moderator.setUsername(moderatorCreateDTO.getUsername());
        moderator.setPassword(moderatorCreateDTO.getPassword());
        moderator.setEmail(moderatorCreateDTO.getEmail());
        moderator.setAvatar(moderatorCreateDTO.getAvatar());
        moderator.setRegistrationDate(moderatorCreateDTO.getRegistrationDate());
        moderator.setDescription(moderatorCreateDTO.getDescription());
        moderator = moderatorService.save(moderator);
        return new ResponseEntity<>(new ModeratorDTO(moderator),HttpStatus.CREATED);
    }
    @PutMapping(consumes = "application/json")
    public ResponseEntity<ModeratorDTO>updateModerator(@RequestBody ModeratorCreateDTO moderatorCreateDTO){
        Moderator moderator = moderatorService.findOne(moderatorCreateDTO.getUsername());
        if(moderator == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        moderator.setPassword(moderatorCreateDTO.getPassword());
        moderator.setEmail(moderatorCreateDTO.getEmail());
        moderator.setAvatar(moderatorCreateDTO.getAvatar());
        moderator.setRegistrationDate(moderatorCreateDTO.getRegistrationDate());
        moderator.setDescription(moderatorCreateDTO.getDescription());
        moderator = moderatorService.save(moderator);
        return new ResponseEntity<>(new ModeratorDTO(moderator),HttpStatus.CREATED);
    }
    @PutMapping("/set/{username}")
    public ResponseEntity<Void>setModerator(@PathVariable String username){
        User user = userService.findOne(username);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Moderator moderator = new Moderator();
        moderator.setUsername(user.getUsername());
        moderator.setPassword(user.getPassword());
        moderator.setEmail(user.getEmail());
        moderator.setAvatar(user.getAvatar());
        moderator.setRegistrationDate(user.getRegistrationDate());
        moderator.setDescription(user.getDescription());
        userService.delete(user.getUsername());
        moderatorService.save(moderator);

        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping(value = "/{username}")
    public ResponseEntity<Void>deleteModerator(@PathVariable String username){
        Moderator moderator = moderatorService.findOne(username);
        if(moderator != null){
            userService.delete(username);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
