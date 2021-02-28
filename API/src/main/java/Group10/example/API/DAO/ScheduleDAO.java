package Group10.example.API.DAO;

import Group10.example.API.Model.*;
import Group10.example.API.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

@Component
public class ScheduleDAO {

    private final ScheduleRepository scheduleRepository;
    private final CourseRepository courseRepository;
    private final LectureRoomRepository lectureRoomRepository;
    private final LecturerRepository lecturerRepository;
    private final AttendanceRepository attendanceRepository;
    private final AttendanceDAO attendanceDAO;

    @Autowired
    public ScheduleDAO(ScheduleRepository scheduleRepository, CourseRepository courseRepository, LectureRoomRepository lectureRoomRepository, LecturerRepository lecturerRepository, AttendanceRepository attendanceRepository, AttendanceDAO attendanceDAO) {
        this.scheduleRepository = scheduleRepository;
        this.courseRepository = courseRepository;
        this.lectureRoomRepository = lectureRoomRepository;
        this.lecturerRepository = lecturerRepository;
        this.attendanceRepository = attendanceRepository;
        this.attendanceDAO = attendanceDAO;
    }

    public Schedule addScheduleItem(Schedule schedule) {
        Optional<Course> course = courseRepository.findById(schedule.getCourseId());
        Optional<LectureRoom> requested = lectureRoomRepository.findById(schedule.getRoomId());
        course.ifPresent(c -> schedule.setCourseNumber(c.getCourseNumber()));
        requested.ifPresent(l -> schedule.setRoomName(l.getRoomName()));
        Schedule s = scheduleRepository.insert(schedule);
        course.ifPresent(c -> {
            c.addScheduleId(schedule.getScheduleId());
            requested.ifPresent(c::addLectureRoom);
            courseRepository.save(c);
        });
        return s;
    }

    public Collection<Schedule> findAllSchedules() {
        return scheduleRepository.findAll();
    }

    public Collection<Schedule> findSchedulesByCourse(String courseID) {
        return scheduleRepository.findByCourseId(courseID);
    }

    public Collection<Schedule> findSchedulesByLecturer(String lecturerID) {
        return scheduleRepository.findByLecturerId(lecturerID);
    }

    public Collection<Schedule> findSchedulesByCourseAndDayOfWeek(String courseId, String dayOfWeek) {
        return scheduleRepository.findByCourseIdAndDayOfWeek(courseId,dayOfWeek);
    }

    public Collection<Schedule> findSchedulesByStudentAndDayOfWeek(String studentId, String dayOfWeek) {
        ArrayList<String> courseIds = attendanceDAO.findCourseIdListByStudentId(studentId);
        ArrayList<Schedule> schedules = new ArrayList<>();
        for(String courseId :courseIds){
            schedules.addAll(scheduleRepository.findByCourseIdAndDayOfWeek(courseId,dayOfWeek));
        }
        return schedules;
    }

    public Collection<Schedule> findSchedulesByStudent(String studentId) {
        ArrayList<String> courseIds = attendanceDAO.findCourseIdListByStudentId(studentId);
        ArrayList<Schedule> schedules = new ArrayList<>();
        for(String courseId :courseIds){
            schedules.addAll(scheduleRepository.findByCourseId(courseId));
        }
        return schedules;
    }

    public Optional<Schedule> updateScheduleById(String scheduleId, ScheduleUpdatePayload scheduleUpdatePayload) {
        Optional<Schedule> schedule = scheduleRepository.findById(scheduleId);
        Optional<Course> course = courseRepository.findById(scheduleUpdatePayload.getCourseId());
        Optional<LectureRoom> lectureRoom = lectureRoomRepository.findById(scheduleUpdatePayload.getRoomId());
        schedule.ifPresent(s -> {
            course.ifPresent(c -> s.setCourseNumber(c.getCourseNumber()));
            lectureRoom.ifPresent(l -> s.setRoomName(l.getRoomName()));
            s.setCourseId(scheduleUpdatePayload.getCourseId());
            s.setLecturerId(scheduleUpdatePayload.getLecturerId());
            s.setRoomId(scheduleUpdatePayload.getRoomId());
            s.setDayOfWeek(scheduleUpdatePayload.getDayOfWeek());
            s.setStartTime(scheduleUpdatePayload.getStartTime());
            s.setEndTime(scheduleUpdatePayload.getEndTime());
            s.setLabOrLecture(scheduleUpdatePayload.getLabOrLecture());
            scheduleRepository.save(s);
        });
        return null;
    }

    public Result deleteScheduleById(String scheduleId) {
        scheduleRepository.deleteById(scheduleId);
        return new Result("success");
    }

    public Result deleteAllSchedulesByCourseId(String courseId) {
        scheduleRepository.removeAllByCourseId(courseId);
        return new Result("success");
    }

    public HashMap<String,ArrayList<ArrayList<String[]>>> findScheduleDetailsByLecturer(String lecturerId) {
        ArrayList<ArrayList<String[]>> scheduleDetails = new ArrayList<>();
        HashMap<String,ArrayList<ArrayList<String[]>>> map = new HashMap<>();
        String[] weekDays = new String[]{"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY"};
        for (int i = 0; i < 5; i++) {
            Collection<Schedule> collection = scheduleRepository.findByLecturerIdAndDayOfWeek(lecturerId,weekDays[i]);
            ArrayList<String[]> list = new ArrayList<>();
            collection.forEach(schedule -> {
                String arr[] = new String[5];
                arr[0] = schedule.getStartTime();
                arr[1] = schedule.getEndTime();
                arr[2] = "event-"+(schedule.getLabOrLecture()+1);
                arr[3] = schedule.getCourseNumber()+((schedule.getLabOrLecture() == 0)?"":" Labs");
                arr[4] = schedule.getRoomName();
                list.add(arr);
            });
            scheduleDetails.add(list);
        }
        map.put("result",scheduleDetails);
        return map;
    }
}
