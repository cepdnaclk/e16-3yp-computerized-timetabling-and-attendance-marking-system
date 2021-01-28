package Group10.example.API.Model;

public class CourseDetails {

    private String courseName;
    private String courseNum;

    public CourseDetails(String courseName, String courseNum) {
        this.courseName = courseName;
        this.courseNum = courseNum;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(String courseNum) {
        this.courseNum = courseNum;
    }
}
