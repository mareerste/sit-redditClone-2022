package rs.ac.uns.ftn.informatika.redditClone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.informatika.redditClone.model.Administrator;
import rs.ac.uns.ftn.informatika.redditClone.model.Moderator;
import rs.ac.uns.ftn.informatika.redditClone.repository.ModeratorRepository;

import java.util.List;

@Service
public class ModeratorService {
    @Autowired
    private ModeratorRepository moderatorRepository;

    public Moderator findOne(String username){return moderatorRepository.getOne(username);}
    public List<Moderator> findAll(){return moderatorRepository.findAll();}
    public Moderator save(Moderator moderator){return moderatorRepository.save(moderator);}
    public void delete(String username){moderatorRepository.deleteById(username);}
    public Moderator update(Moderator moderator){return moderatorRepository.save(moderator);}
}
