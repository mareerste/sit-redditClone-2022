package rs.ac.uns.ftn.informatika.redditClone.service;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.informatika.redditClone.model.User;
import rs.ac.uns.ftn.informatika.redditClone.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    public User findOne(String username){return userRepository.findById(username).orElseGet(null);}
    public List<User> findAll(){return userRepository.findAll();}
    public User save(User user){return userRepository.save(user);}
    public void delete(User user){userRepository.delete(user);}
    public User login(String username, String password){return userRepository.findByUsernameAndPassword(username, password);}
}
