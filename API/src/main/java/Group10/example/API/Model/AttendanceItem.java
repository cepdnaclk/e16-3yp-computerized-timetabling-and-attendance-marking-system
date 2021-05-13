package Group10.example.API.Model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

public class AttendanceItem {
    @NotNull(message = "Attendance Log date cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate date;

    @NotNull(message = "Attendance Log time cannot be null")
    @JsonFormat(pattern = "HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalTime time;

    @NotNull(message = "Lab or Lecture field cannot be null")
    private int lab_or_lecture;//lab = 1,lecture = 0

    @NotNull(message = "present field cannot be null")
    private boolean present;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    public int getLab_or_lecture() {
        return lab_or_lecture;
    }

    public void setLab_or_lecture(int lab_or_lecture) {
        this.lab_or_lecture = lab_or_lecture;
    }

    public AttendanceItem(@NotNull(message = "Attendance Log date cannot be null") LocalDate date, @NotNull(message = "Attendance Log time cannot be null") LocalTime time, @NotNull(message = "Lab or Lecture field cannot be null") int lab_or_lecture, @NotNull(message = "present field cannot be null") boolean present) {
        this.date = date;
        this.time = time;
        this.lab_or_lecture = lab_or_lecture;
        this.present = present;
    }
}
