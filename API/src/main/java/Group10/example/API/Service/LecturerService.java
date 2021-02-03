package Group10.example.API.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Group10.example.API.Model.Group;
import Group10.example.API.Repository.GroupRepository;

@Service
public class LecturerService {
	
	@Autowired 
	GroupRepository groupRepository;
	
	@Autowired
	GroupService groupService;
	
	public Group createStudentGroup(Group group) {
		
		return groupRepository.insert(group);
		
	}
	
	public void deleteStudentGroup(String groupId) {
		
		groupRepository.deleteById(groupId);
		
	}
	
	
	
	

}
