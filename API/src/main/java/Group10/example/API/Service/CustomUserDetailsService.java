package Group10.example.API.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import Group10.example.API.Model.MobileUser;

import java.util.ArrayList;

@Service("sev1")
public class CustomUserDetailsService implements UserDetailsService {


    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        MobileUser[]  users = new MobileUser[3];
        users[0] = new MobileUser("Saubhagya",bCryptPasswordEncoder.encode("abc123"));
        users[1] = new MobileUser("Nuwan",bCryptPasswordEncoder.encode("def123"));
        users[2] = new MobileUser("Erandana",bCryptPasswordEncoder.encode("ghi123"));


        for(MobileUser user : users) {

            if (s.equals(user.getUserName()))
                return new User(user.getUserName(), user.getPassword(), new ArrayList<>());
        }

        throw new UsernameNotFoundException("User not found with username: " + s);

    }
}

