package rs.ac.uns.ftn.informatika.redditClone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.informatika.redditClone.model.dto.FlairCreateDTO;
import rs.ac.uns.ftn.informatika.redditClone.model.dto.FlairDTO;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Flair;
import rs.ac.uns.ftn.informatika.redditClone.service.CommunityService;
import rs.ac.uns.ftn.informatika.redditClone.service.FlairService;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/flair")
public class FlairController {
    @Autowired
    private FlairService flairService;
    @GetMapping
    public ResponseEntity<List<FlairDTO>>getFlairs(){
        List<Flair> flairs = flairService.findAll();
        List<FlairDTO> flairDTOList = new ArrayList<>();
        for (Flair f: flairs) {
            flairDTOList.add(new FlairDTO(f));
        }
        return new ResponseEntity<>(flairDTOList, HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('USER','MODERATOR', 'ADMIN')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<FlairDTO>getFlair(@PathVariable Integer id){
        Flair flair = flairService.findOne(id);
        if(flair == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(new FlairDTO(flair),HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('USER','MODERATOR', 'ADMIN')")
    @PostMapping(consumes = "application/json")
    public ResponseEntity<FlairDTO> saveFlair(@RequestBody FlairCreateDTO flairCreateDTO){
        if (flairCreateDTO.getName().equals("")||flairCreateDTO.getName() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Flair flair = new Flair();
        flair.setName(flairCreateDTO.getName());
        flair = flairService.save(flair);
        return new ResponseEntity<>(new FlairDTO(flair),HttpStatus.CREATED);
    }
    @PreAuthorize("hasAnyRole('USER','MODERATOR', 'ADMIN')")
    @PutMapping(consumes = "application/json")
    public ResponseEntity<FlairDTO> putFlair(@RequestBody FlairDTO flairDTO){
        Flair flair = flairService.findOne(flairDTO.getId());
        if(flair == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        flair.setName(flairDTO.getName());
        flair = flairService.save(flair);
        return new ResponseEntity<>(new FlairDTO(flair),HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('MODERATOR', 'ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void>deleteFlair(@PathVariable Integer id){
        Flair flair = flairService.findOne(id);
        if(flair != null){
            flairService.delete(flair);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}