package Group10.example.API.Model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

public class Schedule {

    @JsonFormat(pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING)
    private LocalDate date;//exam dates
    private String dayOfWeek;//weekly schedule

    @NotNull(message = "Start Time cannot be Null")
    @JsonFormat(pattern = "HH:mm:ss",shape = JsonFormat.Shape.STRING)
    private LocalTime start_time;

    @NotNull(message = "End Time cannot be Null")
    @JsonFormat(pattern = "HH:mm:ss",shape = JsonFormat.Shape.STRING)
    private LocalTime end_time;

    @NotNull(message = "Lab or Lecture cannot be Null")
    private int lab_or_lecture;//lab = 1 , lecture = 0 , exam = 2

    @NotNull(message = "Lecture Room name cannot be Null")
    private String roomName;
    private String course_id;//this will generated


    public Schedule(LocalDate date,String dayOfWeek, LocalTime start_time, LocalTime end_time, int lab_or_lecture,String roomName) {
        this.date = date;
        this.dayOfWeek = dayOfWeek;
        this.start_time = start_time;
        this.end_time = end_time;
        this.lab_or_lecture = lab_or_lecture;
        this.roomName = roomName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalTime getStart_time() {
        return start_time;
    }

    public void setStart_time(LocalTime start_time) {
        this.start_time = start_time;
    }

    public LocalTime getEnd_time() {
        return end_time;
    }

    public void setEnd_time(LocalTime end_time) {
        this.end_time = end_time;
    }

    public int getLab_or_lecture() {
        return lab_or_lecture;
    }

    public void setLab_or_lecture(int lab_or_lecture) {
        this.lab_or_lecture = lab_or_lecture;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }
}
