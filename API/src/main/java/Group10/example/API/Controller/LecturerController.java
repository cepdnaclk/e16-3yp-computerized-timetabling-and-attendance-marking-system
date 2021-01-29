package Group10.example.API.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import Group10.example.API.Repository.GroupRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Group10.example.API.Model.Group;
import Group10.example.API.Model.Student;
import Group10.example.API.Repository.StudentRepository;
import Group10.example.API.Service.GroupService;
import Group10.example.API.Service.LecturerService;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/lec")
public class LecturerController {
	
	@Autowired
	private GroupService groupService;
	
	@Autowired
    StudentRepository studentRepo;
	
	@Autowired
	LecturerService lecturerService;

    @Autowired
    GroupRepository groupRepo;
	
	@PostMapping("/creategroup")
    public HashMap<String,String> createGroup(@RequestBody Group group){

        HashMap<String, String> map = new HashMap<>();
        if(groupRepo.findBygroupName(group.getGroupName())!=null){
            map.put("msg","Group Name is already exists");
            return map;
        }
        groupService.saveGroup(group);
        map.put("msg","successfully created group");
        map.put("group_id",group.getGroupID());
        return map;
    }
	
	@GetMapping(value="/all/students/{group_id}")
    public List<Student> getStudents(@PathVariable("group_id") String groupId){
        Optional<Group> group =groupService.findGroupByID(groupId);
        List<Student> students = new ArrayList<Student>();
        Set<String> stuIDs ;

        System.out.println("................................Hi>...................................................");

        if(group.isPresent()){

            stuIDs = group.get().getStudentList();
            for(String a:stuIDs){

                Optional<Student> student = studentRepo.findById(a);
                student.ifPresent(c->students.add(c));
                if(student.isPresent()){ System.out.println(student);}

            }

        }
        System.out.println("................................Hi1...................................................");

        return students;
    }
	
	@GetMapping(value="/removegroup/{group_id}")
	public HashMap<String,String> removeGroup(String groupId){
		
		HashMap<String, String> map = new HashMap<>();
		lecturerService.deleteStudentGroup(groupId);
		map.put("msg","group deleted successfully");
		return map;
		
	}
	

}
