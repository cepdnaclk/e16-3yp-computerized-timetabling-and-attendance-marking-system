package Group10.example.API.Model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ScheduleUpdateTemplate {
    private String scheduleId;

    @NotNull(message = "course ID cannot be Null")
    private String courseId;

    @NotNull(message = "Lecture Room ID cannot be Null")
    private String roomId;

    @NotNull(message = "Lab or Lecture cannot be Null")
    @Min(value = 0)
    @Max(value = 1)
    private int labOrLecture;//lab = 1 , lecture = 0

    public String getScheduleId() {
        return scheduleId;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getRoomId() {
        return roomId;
    }

    public int getLabOrLecture() {
        return labOrLecture;
    }
}
