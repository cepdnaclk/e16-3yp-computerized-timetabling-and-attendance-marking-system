package Group10.example.API.Service;

import Group10.example.API.Model.Course;
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

        if(!group.isPresent()){
            map.put("msg","Group is not found");
            return map;
        }

        HashSet<String> studentList = group.get().getStudentList();

        for(String a:student) {
            Optional<Student> stu = studentRepo.findById(a);
            if (stu.isPresent()) {
                stu.ifPresent(s -> studentList.add(stu.get().getStudentID()));
            }
            else{
                map.put("msg","Student is not found");
                return map;
            }
        }


        group.ifPresent(g->groupRepository.save(g));
        return map;

    }

    public HashMap<String,Object> removeStudentFromGroup(List<String> students, String ID){
        Optional<Group> group = groupRepository.findById(ID);
        HashMap<String, Object> map = new HashMap<>();

        if(!group.isPresent()){
            map.put("msg","Group is not found");
            return map;

        }

        map.put("msg","student successfully deleted");

        HashSet<String> studentList = group.get().getStudentList();

        if(group.isPresent()) {

            for(String a:students) {
                studentList.remove(a);
            }
        }


        group.ifPresent(g->groupRepository.save(g));
        return map;
    }

    public HashMap<String, String> addLectures(List<String> lecturer, String ID){
        HashMap<String, String> map = new HashMap<>();
        Optional<Group> group = groupRepository.findById(ID);


        if(!group.isPresent()){
            map.put("msg","Group is not found");
            return map;
        }

        HashSet<String> lecList = group.get().lecList;

        int count = 0;
        for(String a:lecturer) {
            Optional<Lecturer> lec = lecRepo.findById(a);
            if (lec.isPresent()) {

                count++;
                lec.ifPresent(l->lecList.add(l.getLectID()));
            }
            else{
                map.put("msg","Lecturer is not found");
                return map;
            }
        }

        if(count==0){
            map.put("msg","No Valid Lecturers Found");
            return map;
        }

        group.ifPresent(g->g.setLecList(lecList));
        group.ifPresent(g->groupRepository.save(g));
        map.put("msg","lecturers successfully added");
        return map;

    }

    public HashMap<String,Object> removeLecFromGroup(List<String> lecturer, String ID){
        Optional<Group> group = groupRepository.findById(ID);
        HashMap<String, Object> map = new HashMap<>();
        map.put("msg","lecturer successfully deleted");

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

    public HashMap<String, Object> addCourse(List<String> courses, String ID){
        HashMap<String, Object> map = new HashMap<>();
        map.put("msg","course successfully added");
        Optional<Group> group = groupRepository.findById(ID);

        if(group==null){
            map.put("msg","Group is not found");
            return map;
        }

        HashSet<String> courseList = group.get().getCourseList();

        for(String a:courses) {
            Optional<Course> course = courseRepo.findById(a);
            if (course.isPresent()) {
                course.ifPresent(c -> courseList.add(course.get().getCourseId()));
            }
            else{
                map.put("msg","Course is not found");
                return map;
            }
        }


        group.ifPresent(g->groupRepository.save(g));
        return map;

    }

    public HashMap<String,Object> removeCourseFromGroup(List<String> courses, String ID){
        Optional<Group> group = groupRepository.findById(ID);
        HashMap<String, Object> map = new HashMap<>();
        map.put("msg","course successfully deleted");

        if(group==null){
            map.put("msg","Group is not found");
            return map;

        }
        HashSet<String> courseList = group.get().getCourseList();
        if(group.isPresent()) {

            for(String a:courses) {
                courseList.remove(a);
            }
        }


        group.ifPresent(g->groupRepository.save(g));
        return map;
    }


}
