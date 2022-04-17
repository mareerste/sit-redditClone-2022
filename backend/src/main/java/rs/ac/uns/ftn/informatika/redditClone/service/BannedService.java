package rs.ac.uns.ftn.informatika.redditClone.service;

import rs.ac.uns.ftn.informatika.redditClone.model.entity.Banned;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Community;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Moderator;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.User;

import java.util.List;

public interface BannedService {
    Banned findOne(Integer id);
    List<Banned> findAll();
    Banned findByUser(User user) ;
    Banned findByUserAndCommunity(User user, Community community);
    Banned save(Banned banned);
    void delete(Banned banned);
    Banned banUser(Moderator moderator, Community community, User user);
    Banned unbanUser(Moderator moderator, Community community, User user);
}
