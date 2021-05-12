package Group10.example.API.Model;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;

public class AttendanceNew {

    @NotNull(message = "Course cannot be null")
    String courseNumber;

    @NotNull(message = "StudentList cannot be null")
    ArrayList<String> studentRegNoList;


    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public ArrayList<String> getStudentRegNoList() {
        return studentRegNoList;
    }

    public void setStudentRegNoList(ArrayList<String> studentRegNoList) {
        this.studentRegNoList = studentRegNoList;
    }
}
