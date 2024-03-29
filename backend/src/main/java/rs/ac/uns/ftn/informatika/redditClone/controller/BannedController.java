package rs.ac.uns.ftn.informatika.redditClone.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.informatika.redditClone.model.dto.BannedDTO;
import rs.ac.uns.ftn.informatika.redditClone.model.dto.UserDTO;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Banned;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Community;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Report;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.User;
import rs.ac.uns.ftn.informatika.redditClone.service.BannedService;
import rs.ac.uns.ftn.informatika.redditClone.service.CommunityService;
import rs.ac.uns.ftn.informatika.redditClone.service.ModeratorService;
import rs.ac.uns.ftn.informatika.redditClone.service.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/banned")
public class BannedController {

    Logger logger = LoggerFactory.getLogger(BannedController.class);

    @Autowired
    private BannedService bannedService;
    @Autowired
    UserService userService;
    @Autowired
    CommunityService communityService;
    @Autowired
    ModeratorService moderatorService;

    @PreAuthorize("hasAnyRole('MODERATOR', 'ADMIN')")
    @GetMapping
    public ResponseEntity<List<BannedDTO>> getBanneds(){
        List<Banned> bannedList = bannedService.findAll();
        List<BannedDTO> bannedDTOList = new ArrayList<>();

        for (Banned b:bannedList) {
            bannedDTOList.add(new BannedDTO(b));
        }
        return new ResponseEntity<>(bannedDTOList, HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('MODERATOR', 'ADMIN')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<BannedDTO>getBanned(@PathVariable Integer id){
        Banned banned = bannedService.findOne(id);
        if (banned == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(new BannedDTO(banned),HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('USER','MODERATOR', 'ADMIN')")
    @GetMapping(value = "/community/{idCommunity}/user/{idUser}")
    public ResponseEntity<Boolean>getBannedForJoin(@PathVariable Integer idCommunity, @PathVariable String idUser){
        User user = userService.findOne(idUser);
        Community community = communityService.findOne(idCommunity);
        if(user == null || community == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Banned banned = bannedService.findByUserAndCommunity(user, community);
        if (banned == null)
            return new ResponseEntity<>(false,HttpStatus.OK);
        return new ResponseEntity<>(true,HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('MODERATOR', 'ADMIN')")
    @DeleteMapping(value = "/community/{idCommunity}/user/{idUser}")
    public ResponseEntity<Void>deleteBannedForUserAndComunity(@PathVariable Integer idCommunity, @PathVariable String idUser){
        User user = userService.findOne(idUser);
        Community community = communityService.findOne(idCommunity);
        if(user == null || community == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Banned banned = bannedService.findByUserAndCommunity(user, community);
        if (banned != null) {
            bannedService.delete(banned);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PreAuthorize("hasAnyRole('MODERATOR', 'ADMIN')")
    @GetMapping(value = "/community/{id}")
    public ResponseEntity<List<BannedDTO>>getBannedForCommunity(@PathVariable Integer id){
        Community community = communityService.findOne(id);
        if(community == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        List<Banned> banned = bannedService.findByCommunity(community);
        List<BannedDTO> bannedDTOList = new ArrayList<>();
        for (Banned b: banned) {
            bannedDTOList.add(new BannedDTO(b));
        }
        return new ResponseEntity<>(bannedDTOList,HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('MODERATOR', 'ADMIN')")
    @GetMapping(value = "/community/{id}/users")
    public ResponseEntity<List<UserDTO>>getBannedUsers(@PathVariable Integer id){
        Community community = communityService.findOne(id);
        if(community == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        List<Banned> banned = bannedService.findByCommunity(community);
        List<UserDTO> bannedDTOList = new ArrayList<>();
        for (Banned b: banned) {
            bannedDTOList.add(new UserDTO(userService.findOne(b.getUser().getUsername())));
        }
        return new ResponseEntity<>(bannedDTOList,HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('MODERATOR', 'ADMIN')")
    @GetMapping(value = "/user/{id}")
    public ResponseEntity<List<BannedDTO>>getBannedForUser(@PathVariable String username){
        User user = userService.findOne(username);
        if(user == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        List<Banned> banned = bannedService.findByUser(user);
        List<BannedDTO> bannedDTOList = new ArrayList<>();
        for (Banned b: banned) {
            bannedDTOList.add(new BannedDTO(b));
        }
        return new ResponseEntity<>(bannedDTOList,HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('MODERATOR', 'ADMIN')")
    @PostMapping(consumes = "application/json")
    public ResponseEntity<BannedDTO> saveBanned(@RequestBody BannedDTO bannedDTO, Authentication authentication){
        if(bannedDTO.getCommunity() == null || bannedDTO.getUser() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Community community = communityService.findOne(bannedDTO.getCommunity().getId());
        User user = userService.findOne(bannedDTO.getUser().getUsername());
        if(community == null || user == null) {
            logger.error("Community not found");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Banned finBan = bannedService.findByUserAndCommunity(user,community);
        if(finBan != null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Banned banned = new Banned();
        banned.setTimestamp(LocalDate.now());
        banned.setCommunity(communityService.findOne(bannedDTO.getCommunity().getId()));
        banned.setUser(userService.findOne(bannedDTO.getUser().getUsername()));
        banned.setModerator(userService.findOne(authentication.getName()));
        banned = bannedService.save(banned);

        logger.info("Ban created for: "+banned.getUser().getUsername() + "  -  " + banned.getTimestamp().toString());
        return new ResponseEntity<>(new BannedDTO(banned), HttpStatus.CREATED);
    }
    @PreAuthorize("hasAnyRole('MODERATOR', 'ADMIN')")
    @PutMapping(consumes = "application/json")
    public ResponseEntity<BannedDTO> updateBanned(@RequestBody BannedDTO bannedDTO){
        Banned banned = bannedService.findOne(bannedDTO.getId());
        if (banned == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        banned.setTimestamp(bannedDTO.getTimestamp());
        banned.setCommunity(communityService.findOne(bannedDTO.getCommunity().getId()));
        banned.setUser(userService.findOne(bannedDTO.getUser().getUsername()));
        banned.setModerator(moderatorService.findOne(bannedDTO.getModerator().getUsername()));
        banned = bannedService.save(banned);
        return new ResponseEntity<>(new BannedDTO(banned), HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('MODERATOR', 'ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteBanned(@PathVariable Integer id) {

        Banned banned = bannedService.findOne(id);
        if (banned != null) {
            bannedService.delete(banned);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAnyRole('MODERATOR', 'ADMIN')")
    @DeleteMapping(value = "/{communityId}/user/{username}")
    public ResponseEntity<Void> deleteBanned(@PathVariable Integer communityId,@PathVariable String username) {

        Banned banned = bannedService.findByUserAndCommunity(userService.findOne(username), communityService.findOne(communityId));
        if (banned != null) {
            bannedService.delete(banned);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
