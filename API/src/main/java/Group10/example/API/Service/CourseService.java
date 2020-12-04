package Group10.example.API.Service;

import Group10.example.API.DAO.CourseDAO;
import Group10.example.API.Model.Course;
import Group10.example.API.Model.CourseUpdatePayLoad;
import Group10.example.API.Model.Log;
import Group10.example.API.Model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

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

    public Optional<Course> getCourseById(String id) {
        return courseDAO.getCourseById(id);
    }

    public Optional<Course> deleteCourseById(String id) {
        return courseDAO.deleteCourseById(id);
    }

    public Optional<Course> updateCourseById(String id, CourseUpdatePayLoad courseUpdatePayLoad) {
        return courseDAO.updateCourseById(id,courseUpdatePayLoad);
    }

    public Collection<Course> findBySemester(int semester) {
        return courseDAO.findBySemester(semester);
    }

    public Optional<Course> addLogItem(String course_id, Log log) {
        return courseDAO.addLogItem(course_id,log);
    }

    public Optional<Course> addScheduleItem(String course_id, Schedule schedule) {
        return courseDAO.addScheduleItem(course_id,schedule);
    }

    public Optional<Course> findByCourseNumber(String courseNumber) {
        return courseDAO.findByCourseNumber(courseNumber);
    }

    public Optional<Course> addLectureRoom(String course_id, String roomId) {
        return courseDAO.addLectureRoom(course_id,roomId);
    }

    public Collection<Log> findAllLogs() {
        return courseDAO.findAllLogs();
    }

    public Collection<Schedule> findAllSchedules() {
        return courseDAO.findAllSchedules();
    }
}