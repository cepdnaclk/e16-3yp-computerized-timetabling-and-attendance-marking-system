package Group10.example.API.DAO;

import Group10.example.API.Model.*;
import Group10.example.API.Repository.CourseRepository;
import Group10.example.API.Repository.LectureRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

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
        c.getTimeTable().forEach(sc -> sc.setCourse_id(c.getCourseId()));
        c.getCourseLog().forEach(lg -> lg.setCourse_id(c.getCourseId()));
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
        course.ifPresent(c -> {
            c.setCourseNumber(courseUpdatePayLoad.getCourseNumber());
            c.setCourseName(courseUpdatePayLoad.getCourseName());
            c.setSemester(courseUpdatePayLoad.getSemester());
            c.setDays(courseUpdatePayLoad.getDays());
            c.setTimeTable(courseUpdatePayLoad.getTimeTable());
            c.setCourseLog(courseUpdatePayLoad.getCourseLog());
            //make a new hashset
            //to store lecture rooms of the updated course object
            //after adding lecture room ids set it to course->lectureRoomIDs
            HashSet<LectureRoomRef> set = new HashSet<>();
            for(Schedule s: courseUpdatePayLoad.getTimeTable()){
                Optional<LectureRoom> lectureRoom = lectureRoomRepository.findByRoomName(s.getRoomName());
                lectureRoom.ifPresent(lr -> set.add(new LectureRoomRef(lr.getRoomId())));
            }
            c.setLectureRoomIDs(set);
            courseRepository.save(c);
        });
        return course;
    }

    public Collection<Course> findBySemesterAndDepartment(int semester,String department) {
        return courseRepository.findBySemesterAndDepartmentName(semester,department);
    }

    public Optional<Course> addLogItem(String course_id, Log log) {
        Optional<Course> course = courseRepository.findById(course_id);
        course.ifPresent(c -> {
            log.setCourse_id(course_id);
            c.addCourseLog(log);
            courseRepository.save(c);
        });
        return course;
    }

    public Optional<Course> addScheduleItem(String course_id, Schedule schedule) {
        Optional<Course> course = courseRepository.findById(course_id);
        schedule.setCourse_id(course_id);
        Optional<LectureRoom> requested = lectureRoomRepository.findByRoomName(schedule.getRoomName());
        course.ifPresent(c -> {
            c.addCourseSchedule(schedule);
            requested.ifPresent(c::addLectureRoom);
            courseRepository.save(c);
        });
        return course;
    }

    public Optional<Course> findByCourseNumber(String courseNumber) {
        return courseRepository.findByCourseNumber(courseNumber);
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

    public Collection<LectureRoom> findLectureRoomsByCourse(String course_id) {
        ArrayList<LectureRoom> lectureRooms = new ArrayList<>();
        Optional<Course> course = courseRepository.findById(course_id);
        course.ifPresent(c -> {
            for(String s: c.getLectureRoomIDs()){
                Optional<LectureRoom> lectureRoom = lectureRoomRepository.findById(s);
                lectureRoom.ifPresent(lectureRooms::add);
            }
        });
        return lectureRooms;
    }

    public Collection<Course> findByLectureRoomRefRoomId(String roomId) {
        return courseRepository.findByLectureRoomIDsContains(new LectureRoomRef(roomId));
    }

    public Optional<Course> findById(String s){
        return courseRepository.findById(s);
    }
}