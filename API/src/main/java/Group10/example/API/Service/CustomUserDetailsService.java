package Group10.example.API.Service;

import Group10.example.API.Model.Admin;
import Group10.example.API.Model.Student;
import Group10.example.API.Repository.AdminRepository;
import Group10.example.API.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import Group10.example.API.Model.MobileUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("sev1")
public class CustomUserDetailsService implements UserDetailsService {


    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    StudentRepository studRepo;

    @Autowired
    AdminRepository adminRepo;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {


        Student student = studRepo.findByuserName(s);
        Admin admin = adminRepo.findByuserName(s);

        List<SimpleGrantedAuthority> roles;

        if(student != null){

            //System.out.println("...............................................hi...............................");

            roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
            return new User(student.getUserName(),student.getPassword(), roles);
        }
        else if(admin != null){

            roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
            return new User(admin.getUserName(),admin.getPassword(), roles);

        }



        throw new UsernameNotFoundException("User not found with username: " + s);

    }
}

