package rs.ac.uns.ftn.informatika.redditClone.service;

import rs.ac.uns.ftn.informatika.redditClone.model.entity.User;

import java.util.List;

public interface UserService {
    User findOne(String username);
    List<User> findAll();
    User save(User user);
    void delete(User user);
    User login(String username, String password);
}
