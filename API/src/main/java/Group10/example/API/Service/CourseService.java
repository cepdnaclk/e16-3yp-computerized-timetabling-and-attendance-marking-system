package Group10.example.API.Service;

import Group10.example.API.DAO.CourseDAO;
import Group10.example.API.DAO.LectureRoomDAO;
import Group10.example.API.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CourseService {

    private final CourseDAO courseDAO;
    private final LectureRoomDAO lectureRoomDAO;

    @Autowired
    public CourseService(CourseDAO courseDAO, LectureRoomDAO lectureRoomDAO) {
        this.courseDAO = courseDAO;
        this.lectureRoomDAO = lectureRoomDAO;
    }

    public Collection<Course> getCourses() {
        return courseDAO.getCourses();
    }

    public Course addCourse(Course course) {
        HashSet<LectureRoomRef> uniqueLectureRooms = new HashSet<>();
        //lectureRooms field of course object will be initially null or Empty
        //in the schedule field of course object there is a roomName field
        //get the lectureRooms according to roomNames and address them to lectureRooms field
        //to get unique lecture rooms used a hashSet
        for (Schedule s:course.getTimeTable()){
            Optional<LectureRoom> lectureRoom= lectureRoomDAO.findByRoomName(s.getRoomName());
            lectureRoom.ifPresent(lr -> uniqueLectureRooms.add(new LectureRoomRef(lr.getRoomId())));
        }
        //unique lectureroom ids will be added t the course
        course.addLectureRooms(uniqueLectureRooms);
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

    public Optional<Course> addScheduleItem(String course_id, Schedule schedule) {
        return courseDAO.addScheduleItem(course_id,schedule);
    }

    public Collection<Schedule> findAllSchedules() {
        return courseDAO.findAllSchedules();
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
}