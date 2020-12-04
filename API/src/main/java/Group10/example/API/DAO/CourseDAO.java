package Group10.example.API.DAO;

import Group10.example.API.Model.*;
import Group10.example.API.Repository.CourseRepository;
import Group10.example.API.Repository.LectureRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Component
public class CourseDAO {

    private final CourseRepository courseRepository;
    private final LectureRoomRepository lectureRoomRepository;

    @Autowired
    public CourseDAO(CourseRepository courseRepository, LectureRoomRepository lectureRoomRepository) {
        this.courseRepository = courseRepository;
        this.lectureRoomRepository = lectureRoomRepository;
    }

    public Collection<Course> getCourses() {
        return courseRepository.findAll();
    }

    public Course addCourse(Course course) {
        Course c = courseRepository.insert(course);
        c.getTimeTable().forEach(sc -> {
            sc.setCourse_id(c.getId());
        });
        return c;
    }

    public Optional<Course> getCourseById(String id) {
        return courseRepository.findById(id);
    }


    public Optional<Course> deleteCourseById(String id) {
        Optional<Course> course = courseRepository.findById(id);
        course.ifPresent(courseRepository::delete);
        return course;
    }

    public Optional<Course> updateCourseById(String id, CourseUpdatePayLoad courseUpdatePayLoad) {
        Optional<Course> course = courseRepository.findById(id);
        course.ifPresent(c -> c.setCourseNumber(courseUpdatePayLoad.getCourseNumber()));
        course.ifPresent(c -> c.setCourseName(courseUpdatePayLoad.getCourseName()));
        course.ifPresent(c -> c.setSemester(courseUpdatePayLoad.getSemester()));
        course.ifPresent(c -> c.setDays(courseUpdatePayLoad.getDays()));
        course.ifPresent(c -> c.setTimeTable(courseUpdatePayLoad.getTimeTable()));
        course.ifPresent(c -> c.setCourseLog(courseUpdatePayLoad.getCourseLog()));
        course.ifPresent(c -> c.setLectureRooms(courseUpdatePayLoad.getLectureRooms()));
        course.ifPresent(courseRepository::save);
        return course;
    }

    public Collection<Course> findBySemester(int semester) {
        return courseRepository.findBySemester(semester);
    }

    public Optional<Course> addLogItem(String course_id, Log log) {
        Optional<Course> course = courseRepository.findById(course_id);
        course.ifPresent(c -> {
            c.addCourseLog(log);
            courseRepository.save(c);
        });
        return course;
    }

    public Optional<Course> addScheduleItem(String course_id, Schedule schedule) {
        Optional<Course> course = courseRepository.findById(course_id);
        course.ifPresent(c -> {
            c.addCourseSchedule(schedule);
            courseRepository.save(c);
        });
        return course;
    }

    public Optional<Course> findByCourseNumber(String courseNumber) {
        return courseRepository.findByCourseNumber(courseNumber);
    }

    public Optional<Course> addLectureRoom(String course_id, String roomId) {
        Optional<Course> course = courseRepository.findById(course_id);
        Optional<LectureRoom> lectureRoom = lectureRoomRepository.findById(roomId);
        course.ifPresent(c -> c.addLectureRoom(lectureRoom));
        course.ifPresent(courseRepository::save);
        return course;
    }

    public Collection<Log> findAllLogs() {
        Collection<Course> courses = courseRepository.findAll();
        ArrayList<Log> logs = new ArrayList<>();
        courses.forEach(c -> {
            if (c.getCourseLog() != null){
                logs.addAll(c.getCourseLog());
            }
        });
        return logs;
    }

    public Collection<Schedule> findAllSchedules() {
        Collection<Course> courses = courseRepository.findAll();
        ArrayList<Schedule> timeTable = new ArrayList<>();
        courses.forEach(c -> {
            if (c.getTimeTable() != null){
                timeTable.addAll(c.getTimeTable());
            }
        });
        return timeTable;
    }
}
