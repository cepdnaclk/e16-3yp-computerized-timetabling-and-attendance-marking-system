package Group10.example.API.Model;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Course {
    private String course_id;
    private String course_name;
    private int semester;
    private int days;

    private static List<Schedule> time_table;
    private static List<Log> course_log;

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
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

    public static List<Schedule> getTime_table() {
        return time_table;
    }

    public static void setTime_table(List<Schedule> time_table) {
        Course.time_table = time_table;
    }

    public static List<Log> getCourse_log() {
        return course_log;
    }

    public static void setCourse_log(List<Log> course_log) {
        Course.course_log = course_log;
    }
}
