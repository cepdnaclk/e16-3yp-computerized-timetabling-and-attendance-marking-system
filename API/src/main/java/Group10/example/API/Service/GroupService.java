package Group10.example.API.Service;

import Group10.example.API.Model.Group;
import Group10.example.API.Model.Lecturer;
import Group10.example.API.Model.Student;
import Group10.example.API.Repository.CourseRepository;
import Group10.example.API.Repository.GroupRepository;
import Group10.example.API.Repository.LecturerRepository;
import Group10.example.API.Repository.StudentRepository;
import com.mongodb.DBRef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class GroupService {

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    StudentRepository studentRepo;

    @Autowired
    LecturerRepository lecRepo;

    @Autowired
    CourseRepository courseRepo;



    public Collection<Group> getAll(){
        return groupRepository.findAll();
    }

    public Optional<Group>  findGroupByID(String id){
        return groupRepository.findById(id);
    }

    public Group saveGroup(Group group){
        return groupRepository.save(group);
    }

    public void deleteGroup(String id){
        groupRepository.deleteById(id);
    }

    public void editGroupName(String id,String name){

        Optional<Group> group = findGroupByID(id);
        if(group.isPresent()){
            group.get().setGroupName(name);
        }


    }

    public HashMap<String, Object> addStudents(List<String> student, String ID){
        HashMap<String, Object> map = new HashMap<>();
        map.put("msg","student successfully added");
        Optional<Group> group = groupRepository.findById(ID);

        if(group==null){
            map.put("msg","Group is not found");
            return map;
        }

        HashSet<String> studentList = group.get().getStudentList();

        for(String a:student) {
            Optional<Student> stu = studentRepo.findById(a);
            if (stu.isPresent()) {
                stu.ifPresent(s -> studentList.add(stu.get().getStudentID()));
            }
        }


        group.ifPresent(g->groupRepository.save(g));
        return map;

    }

    public HashMap<String,Object> removeStudentFromGroup(List<String> students, String ID){
        Optional<Group> group = groupRepository.findById(ID);
        HashMap<String, Object> map = new HashMap<>();
        map.put("msg","student successfully deleted");

        if(group==null){
            map.put("msg","Group is not found");
            return map;

        }
        HashSet<String> studentList = group.get().getStudentList();

        if(group.isPresent()) {

            for(String a:students) {
                studentList.remove(a);
            }
        }


        group.ifPresent(g->groupRepository.save(g));
        return map;
    }

    public HashMap<String, Object> addLectures(List<String> lecturer, String ID){
        HashMap<String, Object> map = new HashMap<>();
        map.put("msg","lecturer successfully added");
        Optional<Group> group = groupRepository.findById(ID);

        if(group==null){
            map.put("msg","Group is not found");
            return map;
        }

        HashSet<String> lecList = group.get().getLecList();

        for(String a:lecturer) {
            Optional<Lecturer> lec = lecRepo.findById(a);
            if (lec.isPresent()) {
                lec.ifPresent(l -> lecList.add(lec.get().getLectID()));
            }
        }


        group.ifPresent(g->groupRepository.save(g));
        return map;

    }

    public HashMap<String,Object> removeLecFromGroup(List<String> lecturer, String ID){
        Optional<Group> group = groupRepository.findById(ID);
        HashMap<String, Object> map = new HashMap<>();
        map.put("msg","student successfully deleted");

        if(group==null){
            map.put("msg","Group is not found");
            return map;

        }
        HashSet<String> lecList = group.get().getLecList();
        if(group.isPresent()) {

            for(String a:lecturer) {
                lecList.remove(a);
            }
        }


        group.ifPresent(g->groupRepository.save(g));
        return map;
    }

    public HashMap<String, Object> addCourse(List<String> lecturer, String ID){
        HashMap<String, Object> map = new HashMap<>();
        map.put("msg","course successfully added");
        Optional<Group> group = groupRepository.findById(ID);

        if(group==null){
            map.put("msg","Group is not found");
            return map;
        }

        HashSet<String> lecList = group.get().getCourseList();

        for(String a:lecturer) {
            Optional<Lecturer> lec = lecRepo.findById(a);
            if (lec.isPresent()) {
                lec.ifPresent(l -> lecList.add(lec.get().getLectID()));
            }
        }


        group.ifPresent(g->groupRepository.save(g));
        return map;

    }

    public HashMap<String,Object> removeCourseFromGroup(List<String> lecturer, String ID){
        Optional<Group> group = groupRepository.findById(ID);
        HashMap<String, Object> map = new HashMap<>();
        map.put("msg","student successfully deleted");

        if(group==null){
            map.put("msg","Group is not found");
            return map;

        }
        HashSet<String> lecList = group.get().getLecList();
        if(group.isPresent()) {

            for(String a:lecturer) {
                lecList.remove(a);
            }
        }


        group.ifPresent(g->groupRepository.save(g));
        return map;
    }


}
