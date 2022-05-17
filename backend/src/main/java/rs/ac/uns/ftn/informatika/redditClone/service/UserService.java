package rs.ac.uns.ftn.informatika.redditClone.service;

import rs.ac.uns.ftn.informatika.redditClone.model.dto.UserCreateDTO;
import rs.ac.uns.ftn.informatika.redditClone.model.dto.UserDTO;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.User;

import java.util.List;

public interface UserService {
    User findOne(String username);
    List<User> findAll();
    User save(UserCreateDTO user);
    User save(User user);
    void delete(String username);
    User login(String username, String password);
    User findByEmail(String email);
}
