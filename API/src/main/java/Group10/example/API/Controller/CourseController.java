package Group10.example.API.Controller;

import Group10.example.API.Model.Course;
import Group10.example.API.Model.CourseUpdatePayLoad;
import Group10.example.API.Model.Log;
import Group10.example.API.Model.Schedule;
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

    @GetMapping("/all")//checked
    public Collection<Course> getCourses(){
        return courseService.getCourses();
    }

    @PostMapping("/add")//checked
    public Course addCourse(@RequestBody Course course){
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
    public Optional<Course> updateCourseById(@PathVariable("id") String id,@RequestBody CourseUpdatePayLoad courseUpdatePayLoad){
        return courseService.updateCourseById(id,courseUpdatePayLoad);
    }

    @GetMapping(value = "/findbysemester/{semester}")//checked
    public Collection<Course> findBySemester(@PathVariable("semester") int semester){
        return courseService.findBySemester(semester);
    }

    @PutMapping(value = "/addlogitem/{id}")//checked
    public Optional<Course> addLogItem(@PathVariable("id") String course_id, @RequestBody Log log){
        return courseService.addLogItem(course_id,log);
    }

    @PutMapping(value = "/addscheduleitem/{id}")
    public Optional<Course> addScheduleItem(@PathVariable("id") String course_id, @RequestBody Schedule schedule){
        return courseService.addScheduleItem(course_id,schedule);
    }

    @PutMapping(value = "/addlectureroom")
    public Optional<Course> addLectureRoom(@RequestParam(name = "courseId") String course_id, @RequestParam(name = "roomId") String roomId){
        return courseService.addLectureRoom(course_id,roomId);
    }

    @GetMapping(value = "/findbycoursenumber/{courseNumber}")
    public Optional<Course> findByCourseNumber(@PathVariable("courseNumber") String courseNumber){
        return courseService.findByCourseNumber(courseNumber);
    }

    @GetMapping(value = "/findAllLogs")//checked
    public Collection<Log> findAllLogs(){
        return courseService.findAllLogs();
    }

    @GetMapping(value = "/findAllSchedules")//checked
    public Collection<Schedule> findAllSchedules(){
        return courseService.findAllSchedules();
    }
}
