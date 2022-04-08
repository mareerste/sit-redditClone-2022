package rs.ac.uns.ftn.informatika.redditClone.service;

import rs.ac.uns.ftn.informatika.redditClone.model.entity.Community;

import java.util.List;

public interface CommunityService {
    public Community findOne(Integer id);
    public List<Community> findAll();
    public Community save(Community community);
    public void delete(Community community);
}
