package rs.ac.uns.ftn.informatika.redditClone.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Flair;
import rs.ac.uns.ftn.informatika.redditClone.repository.FlairRepository;
import rs.ac.uns.ftn.informatika.redditClone.service.FlairService;

import java.util.List;

@Service
public class FlairServiceImpl implements FlairService {
    @Autowired
    private FlairRepository flairRepository;

    @Override
    public Flair findOne(Integer id){return flairRepository.findById(id).orElse(null);}
    @Override
    public List<Flair> findAll(){return flairRepository.findAll();}
    @Override
    public Flair save (Flair flair){return flairRepository.save(flair);}
    @Override
    public void delete (Flair flair){ flairRepository.delete(flair);}

}
