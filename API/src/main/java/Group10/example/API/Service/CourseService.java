package Group10.example.API.Service;

import Group10.example.API.DAO.CourseDAO;
import Group10.example.API.DAO.LectureRoomDAO;
import Group10.example.API.Model.*;
import Group10.example.API.Repository.LectureRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CourseService {

    private final CourseDAO courseDAO;

    @Autowired
    public CourseService(CourseDAO courseDAO ) {
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

    public Collection<Course> findBySemesterAndDepartment(int semester,String department) {
        return courseDAO.findBySemesterAndDepartment(semester,department);
    }

    public Optional<Course> addLogItem(String course_id, Log log) {
        return courseDAO.addLogItem(course_id,log);
    }

    public Collection<Log> findAllLogs() {
        return courseDAO.findAllLogs();
    }

    public Optional<Course> findByCourseNumber(String courseNumber) {
        return courseDAO.findByCourseNumber(courseNumber);
    }

    public Collection<LectureRoom> findLectureRoomsByCourse(String course_id) {
        return courseDAO.findLectureRoomsByCourse(course_id);
    }

    public Collection<Course> findByLectureRoomRefRoomId(String roomId) {
        return courseDAO.findByLectureRoomRefRoomId(roomId);
    }

    public Optional<Course> findById(String s){
        return courseDAO.findById(s);
    }
}