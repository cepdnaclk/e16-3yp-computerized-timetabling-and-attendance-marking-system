package Group10.example.API.Service;

import Group10.example.API.Model.Admin;
import Group10.example.API.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;




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
    
    public void deleteAdmin(String id){
    	
    	adminRepo.deleteById(id);;
    	
    }
    
    public Admin createAdmin(Admin admin){
    	
    	return adminRepo.insert(admin);
    
    }
    
    public Admin modifyAdminDetails(String id,Admin admin){
	
		
	
		Optional<Admin> tmpAdmin = adminRepo.findById(id);
		
		if(tmpAdmin != null) return adminRepo.save(admin);
		
		return null;
   
    }
}
