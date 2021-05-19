package Group10.example.API.Model;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Notification")
public class Notification {
    @Id
    private String notificationId;

    @UniqueElements
    private String scheduleId;

    private String lecturerName;

    private String courseName;

    private String courseNumber;

    private String lecturerRoom;

    private String labOrLecture;

    private String startTime;

    private String dayOfWeek;

    private Boolean status;

    private String message;



    public Notification(@UniqueElements String scheduleId, String startTime , String dayOfWeek, String labOrLecture, String courseNumber, String lecturerRoom) {
//        this.notificationId = UUID.randomUUID().toString().replace("-","");
        this.scheduleId = this.notificationId = scheduleId;
        this.startTime = startTime;
        this.dayOfWeek = dayOfWeek;
        this.status = false;
        this.lecturerRoom = lecturerRoom;
        this.labOrLecture = labOrLecture;
        this.courseNumber = courseNumber;
    }

    public void createMessage(){
        String notify_message = this.courseNumber+" - "+this.courseName+"\n"+this.labOrLecture+
                " will start at "+startTime+" in " +this.lecturerRoom+"\n"
                +"by Dr."+this.lecturerName;
        this.message = notify_message;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public String getNotificationId() {
        return notificationId;
    }


    public String getScheduleId() {
        return scheduleId;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getLecturerRoom() {
        return lecturerRoom;
    }

    public void setLecturerRoom(String lecturerRoom) {
        this.lecturerRoom = lecturerRoom;
    }

    public String getLabOrLecture() {
        return labOrLecture;
    }

    public void setLabOrLecture(String labOrLecture) {
        this.labOrLecture = labOrLecture;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "notificationId='" + notificationId + '\'' +
                ", startTime='" + startTime + '\'' +
                ", dayOfWeek='" + dayOfWeek + '\'' +
                ", status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
