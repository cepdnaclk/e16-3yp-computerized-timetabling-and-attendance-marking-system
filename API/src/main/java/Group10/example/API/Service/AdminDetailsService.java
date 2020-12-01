package Group10.example.API.Service;

import Group10.example.API.Model.Admin;
import Group10.example.API.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdminDetailsService implements UserDetailsService {

    @Autowired
    AdminRepository adminRepo;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Admin admin = adminRepo.findByuserName(userName);
        if(admin==null){
            return null;
        }
        return new AdminDetails(admin);
    }
}
