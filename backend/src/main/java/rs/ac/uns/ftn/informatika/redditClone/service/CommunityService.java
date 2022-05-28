package rs.ac.uns.ftn.informatika.redditClone.service;

import rs.ac.uns.ftn.informatika.redditClone.model.entity.Community;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.User;

import java.util.List;

public interface CommunityService {
    public Community findOne(Integer id);
    public List<Community> findAll();
    public List<Community> findAllByUser(User user);
    public Community save(Community community);
    public void delete(Community community);
}
