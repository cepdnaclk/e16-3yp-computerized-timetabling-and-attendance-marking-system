package Group10.example.API.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalTime;

@Document(collection = "Schedule")
public class Schedule {

    @Id
    public String scheduleId;

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


    public Schedule(String dayOfWeek, String startTime, String endTime, int labOrLecture, String roomId, String lecturerId) {
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.labOrLecture = labOrLecture;
        this.roomId = roomId;
        this.lecturerId = lecturerId;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getLabOrLecture() {
        return labOrLecture;
    }

    public void setLabOrLecture(int labOrLecture) {
        this.labOrLecture = labOrLecture;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(String lecturerId) {
        this.lecturerId = lecturerId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
