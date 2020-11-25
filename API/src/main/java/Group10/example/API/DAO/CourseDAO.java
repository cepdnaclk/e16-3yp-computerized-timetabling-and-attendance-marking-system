package Group10.example.API.DAO;

import Group10.example.API.Model.Course;
import Group10.example.API.Model.CourseUpdatePayLoad;
import Group10.example.API.Repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class CourseDAO {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseDAO(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Collection<Course> getCourses() {
        return courseRepository.findAll();
    }

    public Course addCourse(Course course) {
        return courseRepository.insert(course);
    }

    public Optional<Course> getCourseById(String id) {
        return courseRepository.findById(id);
    }


    public Optional<Course> deleteCourseById(String id) {
        Optional<Course> course = courseRepository.findById(id);
        course.ifPresent(c -> courseRepository.delete(c));
        return course;
    }

    public Optional<Course> updateCourseById(String id, CourseUpdatePayLoad courseUpdatePayLoad) {
        Optional<Course> course = courseRepository.findById(id);
        course.ifPresent(c -> c.setCourse_name(courseUpdatePayLoad.getCourse_name()));
        course.ifPresent(c -> c.setSemester(courseUpdatePayLoad.getSemester()));
        course.ifPresent(c -> c.setDays(courseUpdatePayLoad.getDays()));
        course.ifPresent(c -> c.setTime_table(courseUpdatePayLoad.getTime_table()));
        course.ifPresent(c -> c.setCourse_log(courseUpdatePayLoad.getCourse_log()));
        course.ifPresent(b -> courseRepository.save(b));
        return course;
    }

    public Collection<Course> findBySemester(int semester) {
        return courseRepository.findBySemester(semester);
    }
}
