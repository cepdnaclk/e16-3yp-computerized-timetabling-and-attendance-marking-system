package Group10.example.API.Model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class CourseRegModel {

    @NotNull(message = "student Id cannot be null")
    @NotBlank(message = "student Id cannot be blank")
    private String studentId;

    @NotNull(message = "course Id List cannot be null")
    private List<String> courseList;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public List<String> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<String> courseList) {
        this.courseList = courseList;
    }
}
