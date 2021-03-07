package Group10.example.API.Model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class ScheduleUpdatePayload {

    @NotNull(message = "course ID cannot be Null")
    private String courseId;

    private String courseNumber;

    @NotNull(message = "Lecturer ID cannot be null")
    private String lecturerId;

    @NotNull(message = "Lecture Room ID cannot be Null")
    private String roomId;

    private String roomName;

    @Pattern(regexp = "MONDAY|TUESDAY|WEDNESDAY|THURSDAY|FRIDAY")
    private String dayOfWeek;//weekly schedule

    @NotNull(message = "Start Time cannot be Null")
    @JsonFormat(pattern = "HH:mm",shape = JsonFormat.Shape.STRING)
    @Pattern(regexp = "\\b\\d\\d:\\d\\d\\b",message = "startTime is not in Correct Format")
    private String startTime;

    @NotNull(message = "End Time cannot be Null")
    @JsonFormat(pattern = "HH:mm",shape = JsonFormat.Shape.STRING)
    @Pattern(regexp = "\\b\\d\\d:\\d\\d\\b",message = "endTime is not in Correct Format")
    private String endTime;

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

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public int getLabOrLecture() {
        return labOrLecture;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public String getRoomName() {
        return roomName;
    }
}
