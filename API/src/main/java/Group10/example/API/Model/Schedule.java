package Group10.example.API.Model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public class Schedule {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
     private LocalDate date;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
     private LocalTime start_time;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
     private LocalTime end_time;
     private int lab_or_lecture;
     //lab = 1 , lecture = 0
    //LocalDate localDate = LocalDate.of( 2015 , 6 , 7 );
//    LocalTime time = LocalTime.of(10,43,12);

    public Schedule(LocalDate date, LocalTime start_time, LocalTime end_time, int lab_or_lecture) {
        this.date = date;
        this.start_time = start_time;
        this.end_time = end_time;
        this.lab_or_lecture = lab_or_lecture;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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
}
