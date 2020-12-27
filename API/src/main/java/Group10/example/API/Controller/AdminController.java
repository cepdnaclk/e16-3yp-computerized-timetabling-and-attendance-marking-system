package Group10.example.API.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Group10.example.API.Model.Course;
import Group10.example.API.Model.Group;
import Group10.example.API.Model.Lecturer;
import Group10.example.API.Model.Student;
import Group10.example.API.Model.groupPayLoad;
import Group10.example.API.Repository.CourseRepository;
import Group10.example.API.Repository.LecturerRepository;
import Group10.example.API.Repository.StudentRepository;
import Group10.example.API.Service.AdminService;
import Group10.example.API.Service.GroupService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private GroupService groupService;

    @Autowired
    StudentRepository studentRepo;

    @Autowired
    CourseRepository courseRepo;

    @Autowired
    LecturerRepository lecRepo;
    
    @Autowired
    AdminService adminService;
    
    @PostMapping("/create/studentacc")
    public HashMap<String,String> addNewStudent(@RequestBody Student student){
    	
    	HashMap<String,String> message = new HashMap<String,String>();
    	Student tmpStudent = adminService.insertStudent(student);
    	
    	if(student != null) message.put("message","Student created successfully" );
    	else message.put("message","Student creation failed" );
    	
    	return message;
    	
    }
    
    @PostMapping("/modify/studentacc")
    public HashMap<String,String> modifyStudent(@RequestBody Student student){
    	
    	HashMap<String,String> message = new HashMap<String,String>();
    	Student tmpStudent = adminService.modifyStudent(student);
    	
    	if(student != null) message.put("message","Student modified successfully" );
    	else message.put("message","Student modification failed" );
    	
    	return message;
    	
    }
    
    @PostMapping("/create/lectureracc")
    public HashMap<String,String> addNewStudent(@RequestBody Lecturer lecturer){
    	
    	HashMap<String,String> message = new HashMap<String,String>();
    	Lecturer tmpLecturer = adminService.insertLecturer(lecturer);
    	
    	if(tmpLecturer != null) message.put("message","Lecturer created successfully" );
    	else message.put("message","Lecturer creation failed" );
    	
    	return message;
    	
    }
    
    @PostMapping("/modify/lectureracc")
    public HashMap<String,String> modifyLecturer(@RequestBody Lecturer lecturer){
    	
    	HashMap<String,String> message = new HashMap<String,String>();
    	Lecturer tmpLecturer = adminService.modifyLecturer(lecturer);
    	
    	if(tmpLecturer != null) message.put("message","Lecturer modified successfully" );
    	else message.put("message","Lecturer modification failed" );
    	
    	return message;
    	
    }
    
    @GetMapping(value="/delete/sutdent/{student_id}")
    public void deleteStudent(@PathVariable("student_id") String studentId) {
    	
    	adminService.deleteStudent(studentId);	
    	
    }
    
    @GetMapping(value="/delete/lecturer/{lecturer_id}")
    public void deleteLecturer(@PathVariable("lecturer_id") String lecturerId) {
    	
    	adminService.deleteLecturer(lecturerId);
    	
    }
    	

 

    @PostMapping("/creategroup")
    public HashMap<String,String> createGroup(@RequestBody Group group){
        HashMap<String, String> map = new HashMap<>();
        groupService.saveGroup(group);
        map.put("msg","successfully created group");
        map.put("group_id",group.getGroupID());
        return map;
    }

    @PostMapping("add/lecturers")
    public HashMap<String, Object> addLec(@RequestBody groupPayLoad lecturers){
        HashMap<String, Object> map ;
        List<String> lecList = lecturers.getIdList();
        String groupID = lecturers.getGroupId();
        map  = groupService.addLectures(lecList,groupID);
        return map;

    }

   @GetMapping(value="/all/lecturers/{group_id}")
    public List<Lecturer> getLecturers(@PathVariable("group_id") String groupId){
       Optional<Group> group =groupService.findGroupByID(groupId);
       List<Lecturer> lecs = new ArrayList<Lecturer>();
       Set<String> lecID ;

       if(group.isPresent()){
           lecID = group.get().getLecList();
           for(String a:lecID){

               Optional<Lecturer> lec = lecRepo.findById(a);
               lec.ifPresent(c->lecs.add(c));

           }

       }

       return lecs;
    }


    @PostMapping(value="/remove/lecturers")
    public HashMap<String,Object> removeLec(@RequestBody groupPayLoad lecturers){

        HashMap<String, Object> map;
        String groupId = lecturers.getGroupId();
        List<String> lecList = lecturers.getIdList();
        map = groupService.removeLecFromGroup(lecList,groupId);
        return map;


    }

    @PostMapping("add/students")
    public HashMap<String, Object> addStudents(@RequestBody groupPayLoad students){
        HashMap<String, Object> map ;
        List<String> studentList = students.getIdList();
        String groupID = students.getGroupId();
        map  = groupService.addStudents(studentList,groupID);
        return map;

    }

    @GetMapping(value="/all/students/{group_id}")
    public List<Student> getStudents(@PathVariable("group_id") String groupId){
        Optional<Group> group =groupService.findGroupByID(groupId);
        List<Student> students = new ArrayList<Student>();
        Set<String> stuID ;

        if(group.isPresent()){
            stuID = group.get().getStudentList();
            for(String a:stuID){

                Optional<Student> student = studentRepo.findById(a);
                student.ifPresent(c->students.add(c));

            }

        }

        return students;
    }


    @PostMapping(value="/remove/students")
    public HashMap<String,Object> removeStudent(@RequestBody groupPayLoad students){

        HashMap<String, Object> map ;
        String groupId = students.getGroupId();
        List<String> studentList = students.getIdList();
        map = groupService.removeStudentFromGroup(studentList,groupId);
        return map;


    }

    @PostMapping("add/courses")
    public HashMap<String, Object> addCourse(@RequestBody groupPayLoad course){
        HashMap<String, Object> map ;
        List<String> courseList = course.getIdList();
        String groupID = course.getGroupId();
        map  = groupService.addCourse(courseList,groupID);
        return map;

    }

    @GetMapping(value="/all/courses/{group_id}")
    public List<Course> getCourses(@PathVariable("group_id") String groupId){
        Optional<Group> group =groupService.findGroupByID(groupId);
        List<Course> courses = new ArrayList<Course>();
        Set<String> courseID ;

        if(group.isPresent()){
           courseID = group.get().getCourseList();
            for(String a:courseID){

                Optional<Course> course = courseRepo.findById(a);
                course.ifPresent(c->courses.add(c));

            }

        }

        return courses;
    }


    @PostMapping(value="/remove/courses")
    public HashMap<String,Object> removeCourse(@RequestBody groupPayLoad course){

        HashMap<String, Object> map ;
        String groupId = course.getGroupId();
        List<String> courseList = course.getIdList();
        map = groupService.removeCourseFromGroup(courseList,groupId);
        return map;


    }
}
