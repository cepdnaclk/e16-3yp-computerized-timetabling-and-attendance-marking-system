package Group10.example.API.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.LocalTime;

public class Log {
    //EEEE = day of week
    //HH:mm:ss-HH:mm:ss = startTIme-endTime
    @JsonFormat(pattern = "EEEE HH:mm:ss-HH:mm:ss",shape = JsonFormat.Shape.STRING)
    private String original_datetime;
    @JsonFormat(pattern = "EEEE HH:mm:ss-HH:mm:ss",shape = JsonFormat.Shape.STRING)
    private String temp_datetime;
    @JsonFormat(pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING)
    private String expire_date;
    //course_id field will automatically added
    private String course_id;

    public Log(String original_datetime, String temp_datetime, String expire_date) {
        this.original_datetime = original_datetime;
        this.temp_datetime = temp_datetime;
        this.expire_date = expire_date;
    }

    public String getOriginal_datetime() {
        return original_datetime;
    }

    public void setOriginal_datetime(String original_datetime) {
        this.original_datetime = original_datetime;
    }

    public String getTemp_datetime() {
        return temp_datetime;
    }

    public void setTemp_datetime(String temp_datetime) {
        this.temp_datetime = temp_datetime;
    }

    public String getExpire_date() {
        return expire_date;
    }

    public void setExpire_date(String expire_date) {
        this.expire_date = expire_date;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }
}