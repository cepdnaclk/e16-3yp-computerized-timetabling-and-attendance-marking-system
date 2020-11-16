package Group10.example.API.Model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Log {
    private LocalDate original_date;
    private LocalTime original_time;
    private LocalDate temp_date;
    private LocalTime temp_time;
    private LocalDate expire_date;

    public Log(LocalDate original_date, LocalTime original_time, LocalDate temp_date, LocalTime temp_time, LocalDate expire_date) {
        this.original_date = original_date;
        this.original_time = original_time;
        this.temp_date = temp_date;
        this.temp_time = temp_time;
        this.expire_date = expire_date;
    }

    public LocalDate getOriginal_date() {
        return original_date;
    }

    public void setOriginal_date(LocalDate original_date) {
        this.original_date = original_date;
    }

    public LocalTime getOriginal_time() {
        return original_time;
    }

    public void setOriginal_time(LocalTime original_time) {
        this.original_time = original_time;
    }

    public LocalDate getTemp_date() {
        return temp_date;
    }

    public void setTemp_date(LocalDate temp_date) {
        this.temp_date = temp_date;
    }

    public LocalTime getTemp_time() {
        return temp_time;
    }

    public void setTemp_time(LocalTime temp_time) {
        this.temp_time = temp_time;
    }

    public LocalDate getExpire_date() {
        return expire_date;
    }

    public void setExpire_date(LocalDate expire_date) {
        this.expire_date = expire_date;
    }
}
