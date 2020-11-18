package Group10.example.API.Service;

import Group10.example.API.DAO.CourseDAO;
import Group10.example.API.Model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CourseService {

    private final CourseDAO courseDAO;

    @Autowired
    public CourseService(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    public Collection<Course> getCourses() {
        return courseDAO.getCourses();
    }

    public Course addCourse(Course course) {
        return courseDAO.addCourse(course);
    }
}
