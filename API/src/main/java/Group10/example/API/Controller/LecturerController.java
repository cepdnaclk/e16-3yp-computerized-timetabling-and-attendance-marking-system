package Group10.example.API.Controller;

import java.util.*;

import Group10.example.API.Model.Course;
import Group10.example.API.Model.Lecturer;
import Group10.example.API.Repository.CourseRepository;
import Group10.example.API.Repository.GroupRepository;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    UsersController usersController;

    @Autowired
    CourseRepository courseRepository;

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


    @PostMapping(value = "/addcourse")
    public Optional<Lecturer> addCourseToLecturer(@RequestParam("lecturer")String lecturerId,@RequestParam("course")String courseId){
        System.out.println("addCourseToLecturer Controller");
        return lecturerService.addCourseToLecturer(lecturerId,courseId);
    }

    @DeleteMapping(value = "/deletecourse")
    public Optional<Lecturer> removeCourseToLecturer(@RequestParam("lecturer")String lecturerId,@RequestParam("course")String courseId){
        return lecturerService.removeCourseToLecturer(lecturerId,courseId);
    }

    @GetMapping(value = "/find/all")
    public Collection<Lecturer> findAll(){
        return lecturerService.findAll();
    }

    @GetMapping(value = "/find/{id}")
    public Optional<Lecturer> findById(@PathVariable("id")String id){
        return lecturerService.findById(id);
    }

    @GetMapping(value = "/find/allcourses")
    public Collection<Course> findAllCourses(){
        Lecturer lec = usersController.getLecturerFromSession();
        ArrayList<Course> courses = new ArrayList<>();
        for (String s:lec.getCourseIds()){
            Optional<Course> course = courseRepository.findById(s);
            course.ifPresent(courses::add);
        }
        return courses;
    }

    @GetMapping(value = "/findallcoursesbylectureid/{id}")
    public Collection<Course> findAllCourses(@PathVariable("id")String lecturerId){
        Optional<Lecturer> lecturer = lecturerService.findById(lecturerId);
        ArrayList<Course> courses = new ArrayList<>();
        lecturer.ifPresent(lec -> {
            for (String s:lec.getCourseIds()){
                Optional<Course> course = courseRepository.findById(s);
                course.ifPresent(courses::add);
            }
        });

        return courses;
    }

}
