package Group10.example.API.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Optional;

@Document(collection = "Course")
public class Course {

    @Id
    private String courseId;
    private String courseNumber;
    private String courseName;
    private int semester;
    private int days;

    private List<Schedule> timeTable;
    private List<Log> courseLog;

    @DBRef(lazy = true)
    private List<LectureRoom> lectureRooms;

    public String getCourseId() {
        return courseId;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public List<Schedule> getTimeTable() {
        return timeTable;
    }

    public void setTimeTable(List<Schedule> timeTable) {
        this.timeTable = timeTable;
    }

    public List<Log> getCourseLog() {
        return courseLog;
    }

    public void setCourseLog(List<Log> courseLog) {
        this.courseLog = courseLog;
    }

    public List<LectureRoom> getLectureRooms() {
        return lectureRooms;
    }

    public void setLectureRooms(List<LectureRoom> lectureRooms) {
        this.lectureRooms = lectureRooms;
    }

    public void addCourseLog(Log log){
        this.courseLog.add(log);
    }

    public void addCourseSchedule(Schedule schedule){
        this.timeTable.add(schedule);
    }

    public void addLectureRoom(Optional<LectureRoom> lectureRoom) {
        lectureRoom.ifPresent(lr -> this.lectureRooms.add(lr));
    }
}
