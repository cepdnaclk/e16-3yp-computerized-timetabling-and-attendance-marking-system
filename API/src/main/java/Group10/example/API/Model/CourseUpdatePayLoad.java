package Group10.example.API.Model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CourseUpdatePayLoad {

    @NotNull(message = "Course Number cannot be Null")
    @NotBlank(message = "Course Number cannot be Blank")
    private String courseNumber;

    @NotNull(message = "Course Name cannot be Null")
    @NotBlank(message = "Course Name cannot be Blank")
    private String courseName;

    @NotNull(message = "Semester cannot be Null")
    @Min(value = 1,message = "Minimum value of semester is 1")
    @Max(value = 8,message = "Maximum value of Semester is 8")
    private int semester;

    @NotNull(message = "days cannot be Null")
    @Min(value = 1,message = "Minimum value of Days is 1")
    private int days;

    @NotNull(message = "TimeTable cannot be null")
    private List<Schedule> timeTable;

    @NotNull(message = "Course Log cannot be null")
    private List<Log> courseLog;

    private Set<LectureRoomRef> lectureRoomIDs = new HashSet<>();

    public String getCourseNumber() {
        return courseNumber;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getSemester() {
        return semester;
    }

    public int getDays() {
        return days;
    }

    public List<Schedule> getTimeTable() {
        return timeTable;
    }

    public List<Log> getCourseLog() {
        return courseLog;
    }

    public Set<LectureRoomRef> getLectureRooms() {
        return this.lectureRoomIDs;
    }

}
