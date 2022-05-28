package rs.ac.uns.ftn.informatika.redditClone.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Community;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.User;
import rs.ac.uns.ftn.informatika.redditClone.repository.CommunityRepository;
import rs.ac.uns.ftn.informatika.redditClone.service.CommunityService;

import java.util.List;

@Service
public class CommunityServiceImpl implements CommunityService {
    @Autowired
    private CommunityRepository communityRepository;
    @Override
    public Community findOne(Integer id){return communityRepository.findById(id).orElseGet(null);}
    @Override
    public List<Community> findAll(){return communityRepository.findAll();}

    @Override
    public List<Community> findAllByUser(User user) {
        return communityRepository.findByModerators(user);
    }

    @Override
    public Community save(Community community){return communityRepository.save(community);}
    @Override
    public void delete(Community community){ communityRepository.delete(community);}
}
