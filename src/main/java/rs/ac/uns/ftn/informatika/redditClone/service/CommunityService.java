package rs.ac.uns.ftn.informatika.redditClone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.informatika.redditClone.model.Community;
import rs.ac.uns.ftn.informatika.redditClone.repository.CommentRepository;
import rs.ac.uns.ftn.informatika.redditClone.repository.CommunityRepository;

import java.util.List;

@Service
public class CommunityService {
    @Autowired
    private CommunityRepository communityRepository;

    public Community findOne(Integer id){return communityRepository.findById(id).orElseGet(null);}
    public List<Community> findAll(){return communityRepository.findAll();}
    public Community save(Community community){return communityRepository.save(community);}
    public void delete(Community community){ communityRepository.delete(community);}
}
