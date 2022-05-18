package rs.ac.uns.ftn.informatika.redditClone.security;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.User;
import rs.ac.uns.ftn.informatika.redditClone.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Component
public class WebSecurity {
    @Autowired
    private UserService userService;

    public boolean checkUser(Authentication authentication, HttpServletRequest request, String username) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.findOne(userDetails.getUsername());
        if(username.equals(user.getUsername())) {
            return true;
        }
        return false;
    }
}
