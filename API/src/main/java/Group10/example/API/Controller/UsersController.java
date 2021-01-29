package Group10.example.API.Controller;

import Group10.example.API.Model.Admin;
import Group10.example.API.Model.Lecturer;
import Group10.example.API.Model.Student;
import Group10.example.API.Model.StudentPayload;
import Group10.example.API.Repository.AdminRepository;
import Group10.example.API.Repository.LecturerRepository;
import Group10.example.API.Repository.StudentRepository;
import Group10.example.API.Service.CourseService;
import Group10.example.API.Service.MailService;
import Group10.example.API.Service.StudentService;
import Group10.example.API.Util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@RestController
public class UsersController {

    @Autowired
    StudentRepository stuRepo;

    @Autowired
    MailService mailService;

    @Autowired
    AdminRepository adminRepo;

    @Autowired
    LecturerRepository lecRepo;

    @Autowired
    StudentService studentService;

    @Autowired
    CourseService courseService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    //get student details from session
    private Student getStudentFromToken(@RequestHeader("Authorization") String auth) {
        String token = null;
        if(auth.startsWith("Bearer ")){
            token=auth.substring(7);
        }
        final String user = jwtTokenUtil.getUsernameFromToken(token);
        Student student = stuRepo.findByuserName(user);
        return student;
    }

    //testing aurthorization filters
    @RequestMapping("/admin")
    public String helloAdmin(){
        //take logged user username
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        return "hello " + name;
    }

    @RequestMapping("/student")
    public HashMap<String,Object> helloStu(@RequestHeader("Authorization") String auth){
        System.out.println( "Student called");
        Student stu = getStudentFromToken(auth);
        if(stu==null){
            HashMap<String,Object> map = new HashMap<>();
            map.put("msg","user is not found");
            return map;
        }
        return studentService.getCourselist(stu);

    }

    @RequestMapping("/lecturer")
    public String helloLec(){
        //take logged user username
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        return "hello " + name;
    }

    //for check mail service
    @RequestMapping("/mail")
    public void check(){
        mailService.sendMail("e16399@eng.pdn.ac.lk","check","test");

    }


    @PostMapping(value="/user/registration/student",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public HashMap<String,Object> registerStudent(@Valid @RequestBody Student student)
    {
        HashMap<String,Object> map = new HashMap<>();
        String RegNum = student.getRegNumber();
        student.setUserName(RegNum);
        Student stud = stuRepo.findByuserName(student.getUserName());

        //check whether user is already exists
        if(stud != null) {
            map.put("msg","user Name is already exists Try with different one");
            return map;
        }

        student.setRole("STUDENT");
        String pass = studentService.passGenerate();
        student.setPassword(passwordEncoder.encode(pass));

        stuRepo.save(student);
        studentService.sendMail(student.getEmail(),"Password: "+pass+"   User Name: " + student.getUserName(),"Account Creation");

        //successfully registered and return the registered user
        map.put("Student",stuRepo.findByuserName(student.getUserName()));
        return map;
    }

    @PostMapping(value="/user/registration/admin",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public HashMap<String,Object>  registerAdmin(@Valid @RequestBody Admin adminUser)
    {
        HashMap<String,Object> map = new HashMap<>();

        Admin admin = adminRepo.findByuserName(adminUser.getUserName());
        Lecturer lec = lecRepo.findByuserName(adminUser.getUserName());

        //check whether user is already exists
        if(admin!=null || lec!=null) {
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
        Admin admin = adminRepo.findByuserName(lecturer.getUserName());
        Lecturer lec = lecRepo.findByuserName(lecturer.getUserName());
        //check whether user is already exists
        if(admin!=null || lec!=null) {
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

   @GetMapping(value = "user/all/students")
    public List<Student> findAll(){
        return stuRepo.findAll();
   }



}
