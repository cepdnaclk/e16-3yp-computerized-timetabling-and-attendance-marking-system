package Group10.example.API.Service;

import Group10.example.API.Model.Course;
import Group10.example.API.Model.Group;
import Group10.example.API.Model.Student;
import Group10.example.API.Model.StudentPayload;
import Group10.example.API.Repository.CourseRepository;
import Group10.example.API.Repository.GroupRepository;
import Group10.example.API.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository stuRepo;

    @Autowired
    GroupRepository groupRepo;

    @Autowired
    CourseRepository courseRepo;

    @Autowired
    AttendanceService attService;

    public Student addStudent(Student student){
       return stuRepo.save(student);
    }

    public HashMap<String, Object> deleteStudent(String id){

        HashMap<String,Object> map = new HashMap<>();
        Optional<Student> student = stuRepo.findById(id);
        HashSet<String> groups;
        HashSet<String> students;

        if(student.isPresent()){
            groups = student.get().getGroupSet();
            for(String groupID : groups ){
                Optional<Group> group = groupRepo.findById(groupID);
                if(group.isPresent()){
                    students = group.get().getStudentList();
                    students.remove(id);
                    //save group again
                    group.ifPresent(g->groupRepo.save(g));

                }

            }
        }

        attService.deleteByStudentId(id);
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
