package Group10.example.API.DAO;

import Group10.example.API.Model.*;
import Group10.example.API.Repository.*;
import Group10.example.API.Service.NotificationService;
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
    private final NotificationService notificationService;

    @Autowired
    public ScheduleDAO(ScheduleRepository scheduleRepository, CourseRepository courseRepository, LectureRoomRepository lectureRoomRepository, LecturerRepository lecturerRepository, AttendanceRepository attendanceRepository, AttendanceDAO attendanceDAO, NotificationService notificationService) {
        this.scheduleRepository = scheduleRepository;
        this.courseRepository = courseRepository;
        this.lectureRoomRepository = lectureRoomRepository;
        this.lecturerRepository = lecturerRepository;
        this.attendanceRepository = attendanceRepository;
        this.attendanceDAO = attendanceDAO;
        this.notificationService = notificationService;
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
        //add by schedule id
        notificationService.addByScheduleId(s.getScheduleId());
        return s;
    }

    public ArrayList<Schedule> addScheduleList(ArrayList<Schedule> schedules) {
        for(Schedule s:schedules){
            scheduleRepository.insert(s);
            //add by schedule id
            notificationService.addByScheduleId(s.getScheduleId());
        }
        return schedules;
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
        //update = delete + add
        notificationService.deleteById(scheduleId);
        notificationService.addByScheduleId(scheduleId);
        return schedule;
    }

    public Optional<Schedule> updateScheduleDetails(ScheduleUpdateTemplate scheduleUpdateTemplate) {
        Optional<Schedule> schedule = scheduleRepository.findById(scheduleUpdateTemplate.getScheduleId());
        Optional<Course> course = courseRepository.findById(scheduleUpdateTemplate.getCourseId());
        Optional<LectureRoom> lectureRoom = lectureRoomRepository.findById(scheduleUpdateTemplate.getRoomId());
        schedule.ifPresent(s -> {
            course.ifPresent(c -> s.setCourseId(c.getCourseId()));
            course.ifPresent(c -> s.setCourseNumber(c.getCourseNumber()));
            lectureRoom.ifPresent(l ->s.setRoomId(l.getRoomId()));
            lectureRoom.ifPresent(l -> s.setRoomName(l.getRoomName()));
            s.setLabOrLecture(scheduleUpdateTemplate.getLabOrLecture());
            scheduleRepository.save(s);

            //update = delete + add
            notificationService.deleteById(s.getScheduleId());
            notificationService.addByScheduleId(s.getScheduleId());
        });
        return schedule;
    }

    public Result deleteScheduleById(String scheduleId) {
        scheduleRepository.deleteById(scheduleId);

        //delete notification
        notificationService.deleteById(scheduleId);
        return new Result("success");
    }

    public Result deleteAllSchedulesByCourseId(String courseId) {
        scheduleRepository.removeAllByCourseId(courseId);
        //delete all
        notificationService.deleteAllNotifications();
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
                String arr[] = new String[6];
                arr[0] = schedule.getStartTime();
                arr[1] = schedule.getEndTime();
                arr[2] = "event-"+(schedule.getLabOrLecture()+1);
                arr[3] = schedule.getCourseNumber()+((schedule.getLabOrLecture() == 0)?"":" Labs");
                arr[4] = schedule.getRoomName();
                arr[5] = schedule.getScheduleId();
                list.add(arr);
            });
            scheduleDetails.add(list);
        }
        map.put("result",scheduleDetails);
        return map;
    }

    public HashMap<String, ArrayList<ArrayList<String[]>>> findScheduleDetailsByStudent(String studentId) {
        ArrayList<ArrayList<String[]>> scheduleDetails = new ArrayList<>();
        ArrayList<String> courseIds = attendanceDAO.findCourseIdListByStudentId(studentId);
        HashMap<String,ArrayList<ArrayList<String[]>>> map = new HashMap<>();
        String[] weekDays = new String[]{"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY"};
        for (int i = 0; i < 5; i++) {
            Collection<Schedule> collection = new ArrayList<>();
            for(String courseId :courseIds){
                collection.addAll(scheduleRepository.findByCourseIdAndDayOfWeek(courseId,weekDays[i]));
            }
            scheduleRepository.findByLecturerIdAndDayOfWeek(studentId,weekDays[i]);
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
