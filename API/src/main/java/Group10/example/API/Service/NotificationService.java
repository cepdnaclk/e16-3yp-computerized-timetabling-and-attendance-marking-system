package Group10.example.API.Service;

import Group10.example.API.DAO.NotificationDAO;
import Group10.example.API.Model.Course;
import Group10.example.API.Model.Lecturer;
import Group10.example.API.Model.Notification;
import Group10.example.API.Model.Schedule;
import Group10.example.API.Repository.CourseRepository;
import Group10.example.API.Repository.LectureRoomRepository;
import Group10.example.API.Repository.LecturerRepository;
import Group10.example.API.Repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Service
public class NotificationService {

    private final NotificationDAO notificationDAO;
    private final ScheduleRepository scheduleRepository;
    private final LecturerRepository lecturerRepository;
    private final CourseRepository courseRepository;
    private final LectureRoomRepository lectureRoomRepository;

    @Autowired
    public NotificationService(NotificationDAO notificationDAO, ScheduleRepository scheduleRepository, LecturerRepository
            lecturerRepository, CourseRepository courseRepository, LectureRoomRepository lectureRoomRepository) {
        this.notificationDAO = notificationDAO;
        this.scheduleRepository = scheduleRepository;
        this.lecturerRepository = lecturerRepository;
        this.courseRepository = courseRepository;
        this.lectureRoomRepository = lectureRoomRepository;
    }

    public String addAllNotifications() {
        Collection<Schedule> schedules = scheduleRepository.findAll();
//        System.out.println("No of schedules = "+schedules.size());
        for(Schedule s:schedules){
            Optional<Lecturer> lecturer = lecturerRepository.findById(s.getLecturerId());
            Optional<Course> course = courseRepository.findById(s.getCourseId());
            Notification notification = new Notification(s.getScheduleId(),s.getStartTime(),s.getDayOfWeek(),(s.getLabOrLecture()==0)?"Lecture":"Lab",s.getCourseNumber(),s.getRoomName());
            lecturer.ifPresent(l -> notification.setLecturerName(l.getFirstName()+" "+l.getLastName()));
            course.ifPresent(c -> notification.setCourseName(c.getCourseName()));
            notification.createMessage();
//            System.out.println("Notification id  :"+ notification.getNotificationId());
            notificationDAO.addNotification(notification);
        }
        return "Success";
    }

    public Collection<Notification> getAllNotifications() {
        return notificationDAO.getAllNotifications();
    }

    public void deleteAllNotifications() {
        notificationDAO.deleteAllNotifications();
    }

    public void refreshAll() {
        deleteAllNotifications();
        addAllNotifications();
    }

    public Collection<Notification> findAllByDayOfWeek() {
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE");
        String dayOfWeek = formatter.format(new Date()).toUpperCase();
        return notificationDAO.findAllByDayOfWeek(dayOfWeek);
    }

    public Optional<Notification> deleteById(String id) {
        return notificationDAO.deleteById(id);
    }


    public Optional<Notification> addByScheduleId(String id) {
        Optional<Schedule> schedule = scheduleRepository.findById(id);
        schedule.ifPresent(s -> {
            Optional<Lecturer> lecturer = lecturerRepository.findById(s.getLecturerId());
            Optional<Course> course = courseRepository.findById(s.getCourseId());
            Notification notification = new Notification(s.getScheduleId(),s.getStartTime(),s.getDayOfWeek(),(s.getLabOrLecture()==0)?"Lecture":"Lab",s.getCourseNumber(),s.getRoomName());
            lecturer.ifPresent(l -> notification.setLecturerName(l.getFirstName()+" "+l.getLastName()));
            course.ifPresent(c -> notification.setCourseName(c.getCourseName()));
            notification.createMessage();
            notificationDAO.addNotification(notification);
        });
        return notificationDAO.findById(id);
    }

    public Optional<Notification> findById(String id) {
        return notificationDAO.findById(id);
    }
}
