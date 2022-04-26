package rs.ac.uns.ftn.informatika.redditClone.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.User;
import rs.ac.uns.ftn.informatika.redditClone.repository.UserRepository;
import rs.ac.uns.ftn.informatika.redditClone.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User findOne(String username){return userRepository.findById(username).orElseGet(null);}
    @Override
    public List<User> findAll(){return userRepository.findAll();}
    @Override
    public User save(User user){return userRepository.save(user);}

    @Override
    public void delete(String username) {
        userRepository.deleteById(username);
    }

    @Override
    public User login(String username, String password){return userRepository.findByUsernameAndPassword(username, password);}
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
