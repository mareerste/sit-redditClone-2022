package rs.ac.uns.ftn.informatika.redditClone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.informatika.redditClone.model.dto.AdministratorDTO;
import rs.ac.uns.ftn.informatika.redditClone.model.dto.UserCreateDTO;
import rs.ac.uns.ftn.informatika.redditClone.model.dto.UserDTO;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Administrator;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.User;
import rs.ac.uns.ftn.informatika.redditClone.service.AdministratorService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/administrator")
public class AdministratorController {
    @Autowired
    private AdministratorService administratorService;
    @GetMapping
    public ResponseEntity<List<AdministratorDTO>>getAdmins(){
        List<Administrator> administrators = administratorService.findAll();
        List<AdministratorDTO> administratorDTOList = new ArrayList<>();
        for (Administrator a:administrators) {
            administratorDTOList.add(new AdministratorDTO(a));
        }
        return new ResponseEntity<>(administratorDTOList, HttpStatus.OK);
    }
    @GetMapping(value = "/{username}")
    public ResponseEntity<AdministratorDTO>getAdministrator(@PathVariable String username){
        Administrator administrator = administratorService.findOne(username);
        if(administrator == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(new AdministratorDTO(administrator),HttpStatus.OK);
    }
    @PutMapping(consumes = "application/json")
    public ResponseEntity<AdministratorDTO> updateUser(@RequestBody AdministratorDTO administratorDTO){
        if(administratorDTO.getUsername().equals("")||administratorDTO.getUsername() == null || administratorDTO.getPassword().equals("")||administratorDTO.getPassword() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Administrator admin = administratorService.findOne(administratorDTO.getUsername());
        if(admin == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        admin.setPassword(administratorDTO.getPassword());
        admin.setEmail(administratorDTO.getEmail());
        admin.setAvatar(administratorDTO.getAvatar());
        admin.setRegistrationDate(administratorDTO.getRegistrationDate());
        admin.setDescription(administratorDTO.getDescription());
        admin = administratorService.save(admin);
        return new ResponseEntity<>(new AdministratorDTO(admin),HttpStatus.OK);
    }
}
