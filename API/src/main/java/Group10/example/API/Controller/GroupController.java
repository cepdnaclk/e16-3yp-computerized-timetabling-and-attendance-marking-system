package Group10.example.API.Controller;

import Group10.example.API.Model.Group;
import Group10.example.API.Model.groupPayLoad;
import Group10.example.API.Repository.StudentRepository;
import Group10.example.API.Service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/groups")
public class GroupController {
    private final GroupService groupService;

    @Autowired
    StudentRepository studentRepo;

    @Autowired
    public GroupController(GroupService groupService){
        this.groupService = groupService;
    }

    @PostMapping("/create")
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
    public HashSet<String> getLecturers(@PathVariable("group_id") String groupId){
        Optional<Group> group =groupService.findGroupByID(groupId);
        if(group.isPresent()){
            return group.get().getLecList();
        }

        return new HashSet<>();
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
    public HashSet<String> getStudents(@PathVariable("group_id") String groupId){
        Optional<Group> group =groupService.findGroupByID(groupId);
        if(group.isPresent()){
            return group.get().getStudentList();
        }

        return new HashSet<>();
    }


    @PostMapping(value="/remove/students")
    public HashMap<String,Object> removeStudent(@RequestBody groupPayLoad students){

        HashMap<String, Object> map ;
        String groupId = students.getGroupId();
        List<String> studentList = students.getIdList();
        map = groupService.removeStudentFromGroup(studentList,groupId);
        return map;


    }

    @PostMapping("add/course")
    public HashMap<String, Object> addCourse(@RequestBody groupPayLoad course){
        HashMap<String, Object> map ;
        List<String> studentList = course.getIdList();
        String groupID = course.getGroupId();
        map  = groupService.(studentList,groupID);
        return map;

    }

    @GetMapping(value="/all/courses/{group_id}")
    public HashSet<String> getCourses(@PathVariable("group_id") String groupId){
        Optional<Group> group =groupService.findGroupByID(groupId);
        if(group.isPresent()){
            return group.get().getCourseList();
        }

        return new HashSet<>();
    }


    @PostMapping(value="/remove/courses")
    public HashMap<String,Object> removeCourse(@RequestBody groupPayLoad course){

        HashMap<String, Object> map ;
        String groupId = course.getGroupId();
        List<String> studentList = course.getIdList();
        map = groupService.removeStudentFromGroup(studentList,groupId);
        return map;


    }

}
