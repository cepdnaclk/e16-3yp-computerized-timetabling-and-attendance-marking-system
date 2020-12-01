package Group10.example.API.Controller;

import Group10.example.API.Model.Course;
import Group10.example.API.Model.CourseUpdatePayLoad;
import Group10.example.API.Model.Log;
import Group10.example.API.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/all")
    public Collection<Course> getCourses(){
        return courseService.getCourses();
    }

    @PostMapping("/add")
    public Course addCourse(@RequestBody Course course){
        return courseService.addCourse(course);
    }

    @GetMapping(value = "/find/{id}")
    public Optional<Course> getCourseById(@PathVariable("id") String id){
        return courseService.getCourseById(id);
    }

    @DeleteMapping(value = "/delete/{id}")
    public Optional<Course> deleteCourseById(@PathVariable("id") String id){
        return courseService.deleteCourseById(id);
    }

    @PutMapping(value = "/update/{id}")
    public Optional<Course> updateCourseById(@PathVariable("id") String id,@RequestBody CourseUpdatePayLoad courseUpdatePayLoad){
        return courseService.updateCourseById(id,courseUpdatePayLoad);
    }

    @GetMapping(value = "/findbysemester/{semester}")
    public Collection<Course> findBySemester(@PathVariable("semester") int semester){
        return courseService.findBySemester(semester);
    }

    @PostMapping(value = "/addlogitem/{id}")
    public String addLogItem(@PathVariable("id") String course_id, @RequestBody Log log){
        return courseService.addLogItem(course_id,log);
    }

    @GetMapping(value = "/findlog/{id")
    public List<Log> findLogListByCourseID(@PathVariable("id") String id){
        return courseService.findLogListByCourseID(id);
    }
}
