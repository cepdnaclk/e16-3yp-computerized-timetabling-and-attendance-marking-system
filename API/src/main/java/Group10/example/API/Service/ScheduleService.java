package Group10.example.API.Service;

import Group10.example.API.DAO.ScheduleDAO;
import Group10.example.API.Model.Result;
import Group10.example.API.Model.Schedule;
import Group10.example.API.Model.ScheduleUpdatePayload;
import Group10.example.API.Model.ScheduleUpdateTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

@Service
public class ScheduleService {

    private ScheduleDAO scheduleDAO;

    @Autowired
    public ScheduleService(ScheduleDAO scheduleDAO) {
        this.scheduleDAO = scheduleDAO;
    }

    public Schedule addScheduleItem( Schedule schedule) {
        return scheduleDAO.addScheduleItem(schedule);
    }

    public Collection<Schedule> findAllSchedules() {
        return scheduleDAO.findAllSchedules();
    }

    public Collection<Schedule> findSchedulesByCourse(String courseID) {
        return scheduleDAO.findSchedulesByCourse(courseID);
    }

    public Collection<Schedule> findSchedulesByLecturer(String lecturerID) {
        return scheduleDAO.findSchedulesByLecturer(lecturerID);
    }

    public Collection<Schedule> findSchedulesByCourseAndDayOfWeek(String courseId, String dayOfWeek) {
        return scheduleDAO.findSchedulesByCourseAndDayOfWeek(courseId,dayOfWeek);
    }

    public Collection<Schedule> findSchedulesByStudentAndDayOfWeek(String studentId, String dayOfWeek) {
        return scheduleDAO.findSchedulesByStudentAndDayOfWeek(studentId,dayOfWeek);
    }

    public Collection<Schedule> findSchedulesByStudent(String studentId) {
        return scheduleDAO.findSchedulesByStudent(studentId);
    }

    public Optional<Schedule> updateScheduleById(String scheduleId, ScheduleUpdatePayload scheduleUpdatePayload) {
        return scheduleDAO.updateScheduleById(scheduleId,scheduleUpdatePayload);
    }

    public Result deleteScheduleById(String scheduleId) {
        return scheduleDAO.deleteScheduleById(scheduleId);
    }

    public Result deleteAllSchedulesByCourseId(String courseId) {
        return scheduleDAO.deleteAllSchedulesByCourseId(courseId);
    }

    public HashMap<String,ArrayList<ArrayList<String[]>>> findScheduleDetailsByLecturer(String lecturerId) {
        return scheduleDAO.findScheduleDetailsByLecturer(lecturerId);
    }

    public HashMap<String, ArrayList<ArrayList<String[]>>> findScheduleDetailsByStudent(String studentId) {
        return scheduleDAO.findScheduleDetailsByStudent(studentId);
    }

    public ArrayList<Schedule> addScheduleList(ArrayList<Schedule> schedules) {
        return scheduleDAO.addScheduleList(schedules);
    }

    public Optional<Schedule> updateScheduleDetails(ScheduleUpdateTemplate scheduleUpdateTemplate) {
        return scheduleDAO.updateScheduleDetails(scheduleUpdateTemplate);
    }
}
