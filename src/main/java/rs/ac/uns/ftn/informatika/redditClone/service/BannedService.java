package rs.ac.uns.ftn.informatika.redditClone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.informatika.redditClone.model.Banned;
import rs.ac.uns.ftn.informatika.redditClone.model.User;
import rs.ac.uns.ftn.informatika.redditClone.repository.BannedRepository;

import java.util.List;

@Service
public class BannedService {
    @Autowired
    private BannedRepository bannedRepository;

    public Banned findOne(Integer id){return bannedRepository.findById(id).orElseGet(null);}
    public List<Banned> findAll(){return bannedRepository.findAll();}
    public Boolean findByUser(User user){
        Banned checkUser = bannedRepository.findByUser(user);
        if(checkUser != null){
            return true ;
        } else
            return false;
    }
    public Banned save(Banned banned){return bannedRepository.save(banned);}
    public void delete(Banned banned){ bannedRepository.delete(banned);}
}
