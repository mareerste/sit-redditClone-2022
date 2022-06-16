package rs.ac.uns.ftn.informatika.redditClone.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Banned;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Community;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Moderator;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.User;
import rs.ac.uns.ftn.informatika.redditClone.repository.BannedRepository;
import rs.ac.uns.ftn.informatika.redditClone.service.BannedService;

import java.util.List;

@Service
public class BannedServiceImpl implements BannedService {
    @Autowired
    private BannedRepository bannedRepository;
    @Override
    public Banned findOne(Integer id) {
        return bannedRepository.findById(id).orElse(null);
    }
    @Override
    public List<Banned> findAll() {
        return bannedRepository.findAll();
    }
    @Override
    public List<Banned> findByUser(User user) {
        return bannedRepository.findByUser(user);
    }

    @Override
    public List<Banned> findByCommunity(Community community) {
        return bannedRepository.findByCommunity(community);
    }

    @Override
    public Banned findByUserAndCommunity(User user, Community community) {
        return bannedRepository.findByUserAndCommunity(user, community);
    }
    @Override
    public Banned save(Banned banned) {return bannedRepository.save(banned);}
    @Override
    public void delete(Banned banned) {
        bannedRepository.delete(banned);
    }
    @Override
    public Banned banUser(Moderator moderator, Community community, User user) {
        Banned checkUser = findByUserAndCommunity(user, community);
        if (checkUser == null) {
            Banned banned = new Banned(moderator, community, user);
            return save(banned);
        } else {
            return null;
        }
    }
    @Override
    public Banned unbanUser(Moderator moderator, Community community, User user) {
        Banned checkUser = findByUserAndCommunity(user, community);
        if (checkUser != null) {
            delete(checkUser);
        }
        return checkUser;
    }
}
