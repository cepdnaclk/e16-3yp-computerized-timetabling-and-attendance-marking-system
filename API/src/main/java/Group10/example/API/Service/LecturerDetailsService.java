package Group10.example.API.Service;
import Group10.example.API.Model.Lecturer;
import Group10.example.API.Repository.LecturerRepository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class LecturerDetailsService implements UserDetailsService {

	@Autowired
	private LecturerRepository lecturerRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Lecturer lec = lecturerRepository.findByuserName(userName);
        if(lec==null){
            return null;
        }
        return new LecDetails(lec);
    }
	
	public Collection<Lecturer> getLectures() {
        return lecturerRepository.findAll();
    }

    public Lecturer addLecture( Lecturer lecturer) {
        return lecturerRepository.insert(lecturer);
    }

    public Optional<Lecturer> getLecturerById(String id) {
        return lecturerRepository.findById(id);
    }
    
    public Optional<Lecturer> findLecturerByUserName(String userName){
    	
    	return Optional.of(lecturerRepository.findByuserName(userName));
    }
    
    public void deleteLecturerFromId(String id){
    	
    	lecturerRepository.deleteById(id);
    	
    }
    
	public void deleteLecturerFromUserName(String userName){
		
		Lecturer lecturer = lecturerRepository.findByuserName(userName);
	    	
    	lecturerRepository.deleteById(lecturer.getLectID());
    	
    }
}