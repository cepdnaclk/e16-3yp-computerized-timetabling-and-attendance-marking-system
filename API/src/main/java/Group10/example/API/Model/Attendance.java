package Group10.example.API.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "Attendance")
public class Attendance {
    @Id
    private String attendanceId;
    private String courseId;
    private String studentId;
    private int countLectureDays;
    private int countLabDays;
    private int presentLabDays;
    private int presentLectureDays;
    private double labPercentage;
    private double lecturePercentage;
    private List<AttendanceItem> attendanceItemList;

    public void initialize(){
        setCountLabDays(0);
        setCountLectureDays(0);
        setAttendanceItemList(new ArrayList<>());
        setPresentLabDays(0);
        setPresentLectureDays(0);
        setLabPercentage(100);
        setLecturePercentage(100);
    }

    public void incrementCountLabDays(){
        this.countLabDays++;
    }

    public void incrementCountLectureDays(){
        this.countLectureDays++;
    }

    public void incrementPresentLabDays(){
        this.presentLabDays++;
    }

    public void incrementPresentLectureDays(){
        this.presentLectureDays++;
    }

    public void calculateLabPercentage(){
        setLabPercentage((double)getPresentLabDays()/(double)getCountLabDays()*100);
    }

    public void calculateLecturePercentage(){
        setLecturePercentage((double)getPresentLectureDays()/(double)getCountLectureDays()*100);
    }

    public void addAttendanceItem(AttendanceItem attendanceItem){
        if(attendanceItem.getLab_or_lecture() == 1){ // lab
            incrementCountLabDays();
            if(attendanceItem.isPresent()){
                incrementPresentLabDays();
            }
            calculateLabPercentage();
        }
        else { //lecture
            incrementCountLectureDays();
            if(attendanceItem.isPresent()){
                incrementPresentLectureDays();
            }
            calculateLecturePercentage();
        }
        this.attendanceItemList.add(attendanceItem);
    }

    public String getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(String attendanceId) {
        this.attendanceId = attendanceId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getCountLectureDays() {
        return countLectureDays;
    }

    public void setCountLectureDays(int countLectureDays) {
        this.countLectureDays = countLectureDays;
    }

    public int getCountLabDays() {
        return countLabDays;
    }

    public void setCountLabDays(int countLabDays) {
        this.countLabDays = countLabDays;
    }

    public int getPresentLabDays() {
        return presentLabDays;
    }

    public void setPresentLabDays(int presentLabDays) {
        this.presentLabDays = presentLabDays;
    }

    public int getPresentLectureDays() {
        return presentLectureDays;
    }

    public void setPresentLectureDays(int presentLectureDays) {
        this.presentLectureDays = presentLectureDays;
    }

    public double getLabPercentage() {
        return labPercentage;
    }

    public void setLabPercentage(double labPercentage) {
        this.labPercentage = labPercentage;
    }

    public double getLecturePercentage() {
        return lecturePercentage;
    }

    public void setLecturePercentage(double lecturePercentage) {
        this.lecturePercentage = lecturePercentage;
    }

    public List<AttendanceItem> getAttendanceItemList() {
        return attendanceItemList;
    }

    public void setAttendanceItemList(List<AttendanceItem> attendanceItemList) {
        this.attendanceItemList = attendanceItemList;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "attendanceId='" + attendanceId + '\'' +
                ", courseId='" + courseId + '\'' +
                ", studentId='" + studentId + '\'' +
                ", countLectureDays=" + countLectureDays +
                ", countLabDays=" + countLabDays +
                ", presentLabDays=" + presentLabDays +
                ", presentLectureDays=" + presentLectureDays +
                ", labPercentage=" + labPercentage +
                ", lecturePercentage=" + lecturePercentage +
                ", attendanceItemList=" + attendanceItemList +
                '}';
    }
}