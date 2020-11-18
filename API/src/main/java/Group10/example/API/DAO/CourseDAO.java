package Group10.example.API.DAO;

import Group10.example.API.Model.Course;
import Group10.example.API.Repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

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
}
