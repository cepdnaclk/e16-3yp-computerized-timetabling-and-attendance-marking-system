package Group10.example.API.Model;

import java.util.List;

public class CourseUpdatePayLoad {
    private String course_name;
    private int semester;
    private int days;

    private static List<Schedule> time_table;
    private static List<Log> course_log;

    public String getCourse_name() {
        return course_name;
    }

    public int getSemester() {
        return semester;
    }

    public int getDays() {
        return days;
    }

    public static List<Schedule> getTime_table() {
        return time_table;
    }

    public static List<Log> getCourse_log() {
        return course_log;
    }
}
