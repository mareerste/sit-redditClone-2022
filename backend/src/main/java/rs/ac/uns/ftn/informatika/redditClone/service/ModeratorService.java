package rs.ac.uns.ftn.informatika.redditClone.service;

import rs.ac.uns.ftn.informatika.redditClone.model.entity.Moderator;

import java.util.List;

public interface ModeratorService {
    Moderator findOne(String username);
    List<Moderator> findAll();
    Moderator save(Moderator moderator);
    void delete(String username);
    Moderator update(Moderator moderator);
    void setModerator(String username);
}
