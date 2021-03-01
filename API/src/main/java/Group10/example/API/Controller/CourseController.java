package Group10.example.API.Controller;

import Group10.example.API.Model.*;
import Group10.example.API.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/all")//checked
    public Collection<Course> getCourses(){
        return courseService.getCourses();
    }

    @PostMapping("/add")//checked
    public Course addCourse(@Valid @RequestBody Course course){
        return courseService.addCourse(course);
    }

    @GetMapping(value = "/find/{id}")//checked
    public Optional<Course> getCourseById(@PathVariable("id") String id){
        return courseService.getCourseById(id);
    }

    @DeleteMapping(value = "/delete/{id}")//checked
    public Optional<Course> deleteCourseById(@PathVariable("id") String id){
        return courseService.deleteCourseById(id);
    }

    @PutMapping(value = "/update/{id}")//checked
    public Optional<Course> updateCourseById(@PathVariable("id") String id,@Valid @RequestBody CourseUpdatePayLoad courseUpdatePayLoad){
        return courseService.updateCourseById(id,courseUpdatePayLoad);
    }

    @GetMapping(value = "/findbysemesteranddepartment")//checked
    public Collection<Course> findBySemesterAndDepartment(@RequestParam(name = "semester") int semester,
                                             @RequestParam(name = "department") String department){
        return courseService.findBySemesterAndDepartment(semester,department);
    }

    @PutMapping(value = "/addlogitem/{id}")//checked
    public Optional<Course> addLogItem(@PathVariable("id") String course_id,@Valid @RequestBody Log log){
        return courseService.addLogItem(course_id,log);
    }

    @GetMapping(value = "/findbycoursenumber/{courseNumber}")//checked
    public Optional<Course> findByCourseNumber(@PathVariable("courseNumber") String courseNumber){
        return courseService.findByCourseNumber(courseNumber);
    }

    @GetMapping(value = "/findlogs/all")//checked
    public Collection<Log> findAllLogs(){
        return courseService.findAllLogs();
    }

    // n-m relation between course and lecture room
    @GetMapping(value = "/findlectureroomsbycourse/{id}")
    public Collection<LectureRoom> findLectureRoomsByCourse(@PathVariable("id") String course_id){
        return courseService.findLectureRoomsByCourse(course_id);
    }

    @GetMapping(value = "/findcoursesbylectureroom/{id}")
    public Collection<Course> findByLectureRoomId(@PathVariable("id") String roomId) {
        return courseService.findByLectureRoomRefRoomId(roomId);
    }
}
