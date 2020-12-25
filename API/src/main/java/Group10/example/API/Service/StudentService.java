package Group10.example.API.Service;

import Group10.example.API.Model.Student;
import Group10.example.API.Model.StudentPayload;
import Group10.example.API.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository stuRepo;

    public Student addStudent(Student student){
       return stuRepo.save(student);
    }

    public HashMap<String, Object> deleteStudent(String id){

        HashMap<String,Object> map = new HashMap<>();
        stuRepo.deleteById(id);
        map.put("msg","successfully deleted");

        return map;

    }

    public HashMap<String,Object> updateUserName(Student student){
        HashMap<String,Object> map = new HashMap<>();
        Optional<Student> stu = stuRepo.findById(student.getStudentID());
        stu.ifPresent(s->stu.get().setUserName(student.getUserName()) );
        stu.ifPresent(s->stuRepo.save(s));
        map.put("msg","user name successfully updated");
        return map;
    }

    public HashMap<String,Object> updatePassword(Student student){
        HashMap<String,Object> map = new HashMap<>();
        Optional<Student> stu = stuRepo.findById(student.getStudentID());
        stu.ifPresent(s->stu.get().setUserName(student.getUserName()) );
        stu.ifPresent(s->stuRepo.save(s));
        map.put("msg","user name successfully updated");
        return map;
    }

    public HashMap<String,Object> updateStudent(StudentPayload student){
        HashMap<String,Object> map = new HashMap<>();
        Optional<Student> stu = stuRepo.findById(student.getId());

        if(stu.isPresent() && student.getUserName()!=null) {
            stu.ifPresent(s -> stu.get().setUserName(student.getUserName()));
        }
        if(stu.isPresent() && student.getPassword()!=null) {
            stu.ifPresent(s -> stu.get().setPassword(student.getPassword()));
        }
        stu.ifPresent(s->stuRepo.save(s));
        map.put("msg","user name successfully updated");
        return map;
    }
}
