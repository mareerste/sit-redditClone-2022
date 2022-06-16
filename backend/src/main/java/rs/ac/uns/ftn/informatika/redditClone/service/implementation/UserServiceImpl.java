package rs.ac.uns.ftn.informatika.redditClone.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.informatika.redditClone.model.dto.UserCreateDTO;
import rs.ac.uns.ftn.informatika.redditClone.model.dto.UserDTO;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.User;
import rs.ac.uns.ftn.informatika.redditClone.repository.UserRepository;
import rs.ac.uns.ftn.informatika.redditClone.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public User findOne(String username){return userRepository.findById(username).orElse(null);}
    @Override
    public List<User> findAll(){return userRepository.findAll();}

    @Override
    public User save(UserCreateDTO user) {
        Optional<User> userCheck = Optional.ofNullable(userRepository.findByUsername(user.getUsername()));
        if(userCheck.isPresent())
            return null;

        User newUser = new User();
        newUser.setUsername(user.getUsername());
//        newUser.setPassword(user.getUsername());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setDescription(user.getDescription());
        newUser.setAvatar(user.getAvatar());
        newUser.setEmail(user.getEmail());
        save(newUser);
        return newUser;
    }

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
