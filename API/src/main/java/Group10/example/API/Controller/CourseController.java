package Group10.example.API.Controller;

import Group10.example.API.Model.Course;
import Group10.example.API.Model.CourseUpdatePayLoad;
import Group10.example.API.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
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

    @GetMapping(value = "/{id}")
    public Optional<Course> getCourseById(@PathVariable("id") String id){
        return courseService.getCourseById(id);
    }

    @DeleteMapping(value = "/{id}")
    public Optional<Course> deleteCourseById(@PathVariable("id") String id){
        return courseService.deleteCourseById(id);
    }

    @PutMapping(value = "/{id}")
    public Optional<Course> updateCourseById(@PathVariable("id") String id,@RequestBody CourseUpdatePayLoad courseUpdatePayLoad){
        return courseService.updateCourseById(id,courseUpdatePayLoad);
    }

}
