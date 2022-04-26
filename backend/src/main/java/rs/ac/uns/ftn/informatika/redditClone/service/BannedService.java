package rs.ac.uns.ftn.informatika.redditClone.service;

import rs.ac.uns.ftn.informatika.redditClone.model.entity.Banned;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Community;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Moderator;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.User;

import java.util.List;

public interface BannedService {
    Banned findOne(Integer id);
    List<Banned> findAll();
    List<Banned> findByUser(User user);
    List<Banned> findByCommunity(Community community);
    Banned findByUserAndCommunity(User user, Community community);
    Banned save(Banned banned);
    void delete(Banned banned);
    Banned banUser(Moderator moderator, Community community, User user);
    Banned unbanUser(Moderator moderator, Community community, User user);
}
