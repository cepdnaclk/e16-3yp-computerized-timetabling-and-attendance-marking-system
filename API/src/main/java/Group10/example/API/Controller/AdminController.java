package Group10.example.API.Controller;


import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Group10.example.API.Model.Lecturer;
import Group10.example.API.Model.Student;
import Group10.example.API.Repository.CourseRepository;
import Group10.example.API.Repository.LecturerRepository;
import Group10.example.API.Repository.StudentRepository;
import Group10.example.API.Service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	

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
    	
    	if(tmpStudent != null) message.put("message","Student created successfully" );
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
    	
}
