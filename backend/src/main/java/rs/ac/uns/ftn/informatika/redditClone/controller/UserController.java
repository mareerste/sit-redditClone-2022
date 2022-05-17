package rs.ac.uns.ftn.informatika.redditClone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.informatika.redditClone.model.dto.UserCreateDTO;
import rs.ac.uns.ftn.informatika.redditClone.model.dto.UserDTO;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.User;
import rs.ac.uns.ftn.informatika.redditClone.service.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

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
    @PostMapping(consumes = "application/json")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserCreateDTO newUserDTO){
        if(newUserDTO.getUsername().equals("")||newUserDTO.getUsername() == null || newUserDTO.getPassword().equals("")||newUserDTO.getPassword() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        User user = userService.save(newUserDTO);
        if(user == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(new UserDTO(user),HttpStatus.CREATED);
    }

    @PutMapping(consumes = "application/json")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserCreateDTO userCreateDTO){
        if(userCreateDTO.getUsername().equals("")||userCreateDTO.getUsername() == null || userCreateDTO.getPassword().equals("")||userCreateDTO.getPassword() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        User user = userService.findOne(userCreateDTO.getUsername());
        if(user == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        user.setPassword(userCreateDTO.getPassword());
        user.setEmail(userCreateDTO.getEmail());
        user.setAvatar(userCreateDTO.getAvatar());
        user.setRegistrationDate(userCreateDTO.getRegistrationDate());
        user.setDescription(userCreateDTO.getDescription());
        user = userService.save(user);
        return new ResponseEntity<>(new UserDTO(user),HttpStatus.OK);
    }
    @DeleteMapping(value = "/delete/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username){
        User user = userService.findOne(username);
        if(user != null){
            userService.delete(user.getUsername());
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{username}")
    public ResponseEntity<UserDTO> deleteUserLogic(@PathVariable String username){
        User user = userService.findOne(username);
        if(user != null){
            user.setDeleted(true);
            userService.save(user);
            return new ResponseEntity<>(new UserDTO(user),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/login")
    public ResponseEntity<UserDTO>login(@RequestParam String username, @RequestParam String password){
        User user = userService.login(username,password);
        if(user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(new UserDTO(user),HttpStatus.OK);
    }

    @PutMapping(value = "/changePassword",consumes = "application/json")
    public ResponseEntity<UserDTO> changePassword(@RequestBody UserCreateDTO userCreateDTO){
        if(userCreateDTO.getPassword().equals("")||userCreateDTO.getPassword() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        User user = userService.findOne(userCreateDTO.getUsername());
        if(user == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        user.setPassword(userCreateDTO.getPassword());
        user = userService.save(user);
        return new ResponseEntity<>(new UserDTO(user),HttpStatus.OK);
    }



}
