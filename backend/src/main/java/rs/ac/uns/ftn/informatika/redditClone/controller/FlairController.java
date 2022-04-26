package rs.ac.uns.ftn.informatika.redditClone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.informatika.redditClone.model.dto.FlairCreateDTO;
import rs.ac.uns.ftn.informatika.redditClone.model.dto.FlairDTO;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Flair;
import rs.ac.uns.ftn.informatika.redditClone.service.CommunityService;
import rs.ac.uns.ftn.informatika.redditClone.service.FlairService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/flair")
public class FlairController {
    @Autowired
    private FlairService flairService;
    @Autowired
    private CommunityService communityService;
    @GetMapping
    public ResponseEntity<List<FlairDTO>>getFlairs(){
        List<Flair> flairs = flairService.findAll();
        List<FlairDTO> flairDTOList = new ArrayList<>();
        for (Flair f: flairs) {
            flairDTOList.add(new FlairDTO(f));
        }
        return new ResponseEntity<>(flairDTOList, HttpStatus.OK);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<FlairDTO>getFlair(@PathVariable Integer id){
        Flair flair = flairService.findOne(id);
        if(flair == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(new FlairDTO(flair),HttpStatus.OK);
    }
    @PostMapping(consumes = "application/json")
    public ResponseEntity<FlairDTO> saveFlair(@RequestBody FlairCreateDTO flairCreateDTO){
        if (flairCreateDTO.getName().equals("")||flairCreateDTO.getName() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Flair flair = new Flair();
        flair.setName(flairCreateDTO.getName());
        flair.setCommunity(communityService.findOne(flairCreateDTO.getCommunity().getId()));
        flair = flairService.save(flair);
        return new ResponseEntity<>(new FlairDTO(flair),HttpStatus.CREATED);
    }

    @PutMapping(consumes = "application/json")
    public ResponseEntity<FlairDTO> putFlair(@RequestBody FlairDTO flairDTO){
        Flair flair = flairService.findOne(flairDTO.getId());
        if(flair == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        flair.setName(flairDTO.getName());
        flair = flairService.save(flair);
        return new ResponseEntity<>(new FlairDTO(flair),HttpStatus.OK);
    }
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