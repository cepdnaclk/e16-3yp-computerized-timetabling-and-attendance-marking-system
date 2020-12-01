package Group10.example.API.Service;
import Group10.example.API.Model.Lecturer;
import Group10.example.API.Repository.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LecturerDetailsService implements UserDetailsService {

    @Autowired
    LecturerRepository lecRepo;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Lecturer lec = lecRepo.findByuserName(userName);
        if(lec==null){
            return null;
        }
        return new LecDetails(lec);
    }
}