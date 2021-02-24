package Group10.example.API.Model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalTime;

public class ScheduleUpdatePayload {

    @NotNull(message = "course ID cannot be Null")
    private String courseId;

    @NotNull(message = "Lecturer ID cannot be null")
    private String lecturerId;

    @NotNull(message = "Lecture Room ID cannot be Null")
    private String roomId;

    @Pattern(regexp = "MONDAY|TUESDAY|WEDNESDAY|THURSDAY|FRIDAY")
    private String dayOfWeek;//weekly schedule

    @NotNull(message = "Start Time cannot be Null")
    @JsonFormat(pattern = "HH:mm:ss",shape = JsonFormat.Shape.STRING)
    private LocalTime startTime;

    @NotNull(message = "End Time cannot be Null")
    @JsonFormat(pattern = "HH:mm:ss",shape = JsonFormat.Shape.STRING)
    private LocalTime endTime;

    @NotNull(message = "Lab or Lecture cannot be Null")
    @Min(value = 0)
    @Max(value = 1)
    private int labOrLecture;//lab = 1 , lecture = 0

    public String getCourseId() {
        return courseId;
    }

    public String getLecturerId() {
        return lecturerId;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public int getLabOrLecture() {
        return labOrLecture;
    }
}
