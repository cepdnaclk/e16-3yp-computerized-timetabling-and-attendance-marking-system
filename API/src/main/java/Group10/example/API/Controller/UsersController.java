package Group10.example.API.Controller;

import Group10.example.API.Model.Admin;
import Group10.example.API.Model.Lecturer;
import Group10.example.API.Model.Student;
import Group10.example.API.Repository.AdminRepository;
import Group10.example.API.Repository.LecturerRepository;
import Group10.example.API.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UsersController {

    @Autowired
    StudentRepository stuRepo;

    @Autowired
    AdminRepository adminRepo;

    @Autowired
    LecturerRepository lecRepo;

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
    public String registerStudent(@Valid @RequestBody Student student)
    {
        Student stud = stuRepo.findByuserName(student.getUserName());
        Admin admin = adminRepo.findByuserName(student.getUserName());
        Lecturer lec = lecRepo.findByuserName(student.getUserName());
        //check whether user is already exists
        if(stud != null || admin!=null || lec!=null) {
            return "User Name is already exist";
        }
        student.setRole("STUDENT");
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        stuRepo.save(student);
        //successfully registered and return the registered user
        return "successfully registred";
    }

    @PostMapping(value="/user/registration/admin",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String registerAdmin(@Valid @RequestBody Admin adminUser)
    {
        Student stud = stuRepo.findByuserName(adminUser.getUserName());
        Admin admin = adminRepo.findByuserName(adminUser.getUserName());
        Lecturer lec = lecRepo.findByuserName(adminUser.getUserName());

        //check whether user is already exists
        if(stud != null || admin!=null || lec!=null) {
            return "User Name is already exist";
        }
        adminUser.setRole("ADMIN");
        adminUser.setPassword(passwordEncoder.encode(adminUser.getPassword()));
        adminRepo.save(adminUser);
        //successfully registered and return the registered user
        return "successfully registred";
    }

    @PostMapping(value="/user/registration/lecturer",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String registerLecturer(@Valid @RequestBody Lecturer lecturer)
    {
        Student stud = stuRepo.findByuserName(lecturer.getUserName());
        Admin admin = adminRepo.findByuserName(lecturer.getUserName());
        Lecturer lec = lecRepo.findByuserName(lecturer.getUserName());
        //check whether user is already exists
        if(stud != null || admin!=null || lec!=null) {
            return "User Name is already exist";
        }
        lecturer.setRole("LECTURER");
        lecturer.setPassword(passwordEncoder.encode(lecturer.getPassword()));
        lecRepo.save(lecturer);
        //successfully registered and return the registered user
        return "successfully registred";
    }

}
