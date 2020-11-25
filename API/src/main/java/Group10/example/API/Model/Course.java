package Group10.example.API.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Course")
public class Course {

    @Id
    private String course_id;
    private String course_name;
    private int semester;
    private int days;

    private List<Schedule> time_table;
    private List<Log> course_log;

    @DBRef(lazy = true)
    private List<LectureRoom> lectureRooms;

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getCourse_id() {
        return course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
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

    public List<Schedule> getTime_table() {
        return time_table;
    }

    public void setTime_table(List<Schedule> time_table) {
        this.time_table = time_table;
    }

    public List<Log> getCourse_log() {
        return course_log;
    }

    public void setCourse_log(List<Log> course_log) {
        this.course_log = course_log;
    }

    public List<LectureRoom> getLectureRooms() {
        return lectureRooms;
    }

    public void setLectureRooms(List<LectureRoom> lectureRooms) {
        this.lectureRooms = lectureRooms;
    }
}
