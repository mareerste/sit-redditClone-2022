package rs.ac.uns.ftn.informatika.redditClone.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Moderator;
import rs.ac.uns.ftn.informatika.redditClone.repository.ModeratorRepository;
import rs.ac.uns.ftn.informatika.redditClone.service.ModeratorService;

import java.util.List;

@Service
public class ModeratorServiceImpl implements ModeratorService {
    @Autowired
    private ModeratorRepository moderatorRepository;
    @Override
    public Moderator findOne(String username){return moderatorRepository.getOne(username);}
    @Override
    public List<Moderator> findAll(){return moderatorRepository.findAll();}
    @Override
    public Moderator save(Moderator moderator){return moderatorRepository.save(moderator);}
    @Override
    public void delete(String username){moderatorRepository.deleteById(username);}
    @Override
    public Moderator update(Moderator moderator){return moderatorRepository.save(moderator);}
}
