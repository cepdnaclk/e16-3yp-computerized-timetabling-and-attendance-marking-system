package Group10.example.API.Controller;

import Group10.example.API.Model.Admin;
import Group10.example.API.Model.Lecturer;
import Group10.example.API.Model.Student;
import Group10.example.API.Model.StudentPayload;
import Group10.example.API.Repository.AdminRepository;
import Group10.example.API.Repository.LecturerRepository;
import Group10.example.API.Repository.StudentRepository;
import Group10.example.API.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.HashSet;

@RestController
public class UsersController {

    @Autowired
    StudentRepository stuRepo;

    @Autowired
    AdminRepository adminRepo;

    @Autowired
    LecturerRepository lecRepo;

    @Autowired
    StudentService studentService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    //testing aurthorization filters
    @RequestMapping("/admin")
    public String helloAdmin(){
        return "hello admin";
    }

    @RequestMapping("/student")
    public String helloStu(){
        return "hello student";
    }

    @RequestMapping("/lecturer")
    public String helloLec(){
        return "hello lecturer";
    }

    @PostMapping(value="/user/registration/student",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public HashMap<String,Object> registerStudent(@Valid @RequestBody Student student)
    {
        HashMap<String,Object> map = new HashMap<>();
        Student stud = stuRepo.findByuserName(student.getUserName());
        Admin admin = adminRepo.findByuserName(student.getUserName());
        Lecturer lec = lecRepo.findByuserName(student.getUserName());
        //check whether user is already exists
        if(stud != null || admin!=null || lec!=null) {
            map.put("msg","user Name is already exists");
            return map;
        }
        student.setRole("STUDENT");
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        stuRepo.save(student);
        //successfully registered and return the registered user
        map.put("Student",stuRepo.findByuserName(student.getUserName()));
        return map;
    }

    @PostMapping(value="/user/registration/admin",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public HashMap<String,Object>  registerAdmin(@Valid @RequestBody Admin adminUser)
    {
        HashMap<String,Object> map = new HashMap<>();
        Student stud = stuRepo.findByuserName(adminUser.getUserName());
        Admin admin = adminRepo.findByuserName(adminUser.getUserName());
        Lecturer lec = lecRepo.findByuserName(adminUser.getUserName());

        //check whether user is already exists
        if(stud != null || admin!=null || lec!=null) {
            map.put("msg","user Name is already exists");
            return map;
        }
        adminUser.setRole("ADMIN");
        adminUser.setPassword(passwordEncoder.encode(adminUser.getPassword()));
        adminRepo.save(adminUser);
        //successfully registered and return the registered user
        map.put("Admin",adminRepo.findByuserName(adminUser.getUserName()));
        return map;
    }

    @PostMapping(value="/user/registration/lecturer",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public HashMap<String,Object> registerLecturer(@Valid @RequestBody Lecturer lecturer)
    {
        HashMap<String,Object> map = new HashMap<>();
        Student stud = stuRepo.findByuserName(lecturer.getUserName());
        Admin admin = adminRepo.findByuserName(lecturer.getUserName());
        Lecturer lec = lecRepo.findByuserName(lecturer.getUserName());
        //check whether user is already exists
        if(stud != null || admin!=null || lec!=null) {
            map.put("msg","user Name is already exists");
            return map;
        }
        lecturer.setRole("LECTURER");
        lecturer.setPassword(passwordEncoder.encode(lecturer.getPassword()));
        lecRepo.save(lecturer);
        //successfully registered and return the registered user
        map.put("Lecturer",lecRepo.findByuserName(lecturer.getUserName()));
        return map;
    }

    @PostMapping(value="user/student/delete")
    public HashMap<String, Object> deleteStudent(@PathVariable("student_id") String id){
        HashMap<String,Object> map = new HashMap<>();
        map = studentService.deleteStudent(id);
        return map;

    }

   @PostMapping(value="user/student/update")
   public HashMap<String, Object> updateStudent(@RequestBody StudentPayload stu){
      HashMap<String,Object> map ;
      map = studentService.updateStudent(stu);
      return map;
   }
}
