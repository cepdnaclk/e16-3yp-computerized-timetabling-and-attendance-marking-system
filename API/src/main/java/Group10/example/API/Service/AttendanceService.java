package Group10.example.API.Service;

import Group10.example.API.DAO.AttendanceDAO;
import Group10.example.API.DAO.CourseDAO;
import Group10.example.API.Model.*;
import Group10.example.API.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class AttendanceService {
    private final AttendanceDAO attendanceDAO;
    private final CourseDAO courseDAO;
    private final StudentRepository studentRepository;

    @Autowired
    public AttendanceService(AttendanceDAO attendanceDAO, CourseDAO courseDAO, StudentRepository studentRepository) {
        this.attendanceDAO = attendanceDAO;
        this.courseDAO = courseDAO;
        this.studentRepository = studentRepository;
    }

    public String registerCourses(CourseRegModel courseRegModel) {
        return attendanceDAO.registerCourses(courseRegModel);
    }

    public Collection<Attendance> findAll() {
        return attendanceDAO.findAll();
    }

    public Collection<Course> findCoursesByStudentId(String studentId) {
        return attendanceDAO.findCoursesByStudentId(studentId);
    }

    public Collection<Student> findStudentsByCourseId(String courseId) {
        return attendanceDAO.findStudentsByCourseId(courseId);
    }

    public Attendance findAttendanceByStudentAndCourse(String courseId, String studentId) {
        return attendanceDAO.findAttendanceByStudentAndCourse(courseId,studentId);
    }

    public String addAttendanceLog(String courseId, String studentId, AttendanceItem attendanceItem) {
        return attendanceDAO.addAttendanceLog(courseId,studentId,attendanceItem);
    }

    public ArrayList<String> findCourseIdListByStudentId(String studentId) {
        return attendanceDAO.findCourseIdListByStudentId(studentId);
    }

    public Collection<String> findStudentIdListByCourseId(String courseId) {
        return attendanceDAO.findStudentIdListByCourseId(courseId);
    }

    public void deleteByStudentId(String studentId) {
        attendanceDAO.deleteByStudentId(studentId);
    }

    public void deleteByCourseId(String courseId) {
        attendanceDAO.deleteByCourseId(courseId);
    }

    public String addCompleteAttendanceRecord(AttendanceLogList attendanceLogList) {
        return attendanceDAO.addCompleteAttendanceRecord(attendanceLogList);
    }

    public Collection<AttendanceTemplate> findAttendancesByCourseId(String courseId) {
        return attendanceDAO.findAttendancesByCourseId(courseId);
    }

    public String addRecords(AttendanceNew attendanceNew) {
        AttendanceLogList attendanceLogList = new AttendanceLogList();
        Optional<Course> course = courseDAO.findByCourseNumber(attendanceNew.getCourseNumber());
        course.ifPresent(c -> attendanceLogList.setCourseId(c.getCourseId()));
        ArrayList<String> studentIds = new ArrayList<>();
        for(String s:attendanceNew.getStudentRegNoList()){
            Optional<Student> student = studentRepository.findByRegNumber(s);
            student.ifPresent(stu -> {
                studentIds.add(stu.getStudentID());
            });
        }
        attendanceLogList.setStudentIdList(studentIds);

        Date datetime = Calendar.getInstance().getTime();
        // create a clock
        Clock cl = Clock.systemUTC();
        LocalDate date = LocalDate.now(cl);
        attendanceLogList.setDate(date);

        Date time = new Date();
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("Asia/Colombo"));
        String s = df.format(time);
        LocalTime localTime = LocalTime.parse(s);
        attendanceLogList.setTime(localTime);

        attendanceLogList.setLab_or_lecture(0);
        return attendanceDAO.addCompleteAttendanceRecord(attendanceLogList);
    }
}
