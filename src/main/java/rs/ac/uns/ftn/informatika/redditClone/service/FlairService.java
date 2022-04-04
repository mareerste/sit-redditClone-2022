package rs.ac.uns.ftn.informatika.redditClone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.informatika.redditClone.model.Flair;
import rs.ac.uns.ftn.informatika.redditClone.repository.FlairRepository;

import java.util.List;

@Service
public class FlairService {
    @Autowired
    private FlairRepository flairRepository;

    public Flair findOne(Integer id){return flairRepository.findById(id).orElseGet(null);}
    public List<Flair> findAll(){return flairRepository.findAll();}
    public Flair save (Flair flair){return flairRepository.save(flair);}
    public void delete (Flair flair){ flairRepository.delete(flair);}

}
