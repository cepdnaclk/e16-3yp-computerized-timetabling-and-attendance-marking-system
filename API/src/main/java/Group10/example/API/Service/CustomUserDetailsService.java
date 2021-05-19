package Group10.example.API.Service;

import Group10.example.API.Model.*;
import Group10.example.API.Repository.AdminRepository;
import Group10.example.API.Repository.HardwareDeviceRepository;
import Group10.example.API.Repository.LecturerRepository;
import Group10.example.API.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    @Autowired
    LecturerRepository lecRepo;

    @Autowired
    HardwareDeviceRepository hardwareRepo;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {


        Student student = studRepo.findByuserName(s);
        Admin admin = adminRepo.findByuserName(s);
        Lecturer lecturer = lecRepo.findByuserName(s);
        HardwareDevice hardwareDevice = hardwareRepo.findByUserName(s);

        List<SimpleGrantedAuthority> roles;

        if(student != null){

            //System.out.println("...............................................hi...............................");

            roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_STUDENT"));
            return new User(student.getUserName(),student.getPassword(), roles);
        }
        else if(admin != null){

            roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
            return new User(admin.getUserName(),admin.getPassword(), roles);

        }
        else if(lecturer != null){

            roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_LECTURER"));
            return new User(lecturer.getUserName(),lecturer.getPassword(), roles);

        }
        else if(hardwareDevice != null){

            roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_HARDWARE"));
            return new User(hardwareDevice.getUserName(),hardwareDevice.getPassword(), roles);

        }



        throw new UsernameNotFoundException("User not found with username: " + s);

    }
}

