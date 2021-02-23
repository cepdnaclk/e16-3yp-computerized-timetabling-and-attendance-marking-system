package Group10.example.API.Service;

import Group10.example.API.DAO.AttendanceDAO;
import Group10.example.API.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class AttendanceService {
    private final AttendanceDAO attendanceDAO;

    @Autowired
    public AttendanceService(AttendanceDAO attendanceDAO) {
        this.attendanceDAO = attendanceDAO;
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
}
