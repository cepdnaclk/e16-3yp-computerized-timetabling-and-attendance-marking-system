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

@Component
public class CourseDAO {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private LectureRoomRepository lectureRoomRepository;

    public Collection<Course> getCourses() {
        return courseRepository.findAll();
    }

    public Course addCourse(Course course) {
        Course c =  courseRepository.insert(course);
        c.getTimeTable().forEach(sc -> sc.setCourse_id(c.getCourseId()));
        courseRepository.save(c);
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
        course.ifPresent(courseRepository::save);
        return course;
    }

    public Collection<Course> findBySemester(int semester) {
        return courseRepository.findBySemester(semester);
    }

    public Optional<Course> addLogItem(String course_id, Log log) {
        Optional<Course> course = courseRepository.findById(course_id);
        course.ifPresent(c -> c.addCourseLog(log));
        return course;
    }

    public Optional<Course> addScheduleItem(String course_id, Schedule schedule) {
        Optional<Course> course = courseRepository.findById(course_id);
        course.ifPresent(c -> c.addCourseSchedule(schedule));
        return course;
    }

    public Collection<Log> findAllLogs() {
        Collection<Course> courses = courseRepository.findAll();
        ArrayList<Log> logs = new ArrayList<>();
        courses.forEach(c -> {
            logs.addAll(c.getCourseLog());
        });
        return logs;
    }

    public Collection<Schedule> findAllSchedules() {
        Collection<Course> courses = courseRepository.findAll();
        ArrayList<Schedule> schedules = new ArrayList<>();
        courses.forEach(c -> {
            schedules.addAll(c.getTimeTable());
        });
        return schedules;
    }

    public Optional<Course> addLectureRoomToCourse(String courseId, String roomId) {
        Optional<Course> course = courseRepository.findById(courseId);
        Optional<LectureRoom> lectureRoom = lectureRoomRepository.findById(roomId);
        course.ifPresent(c -> c.addLectureRoom(lectureRoom));
        course.ifPresent(courseRepository::save);
        return course;
    }
}
