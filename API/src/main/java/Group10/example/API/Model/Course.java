package Group10.example.API.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

@Document(collection = "Course")
public class Course {

    @Id
    private String courseId;

    @NotNull(message = "Course Number cannot be Null")
    @NotBlank(message = "Course Number cannot be Blank")
    private String courseNumber;

    @NotNull(message = "Course Name cannot be Null")
    @NotBlank(message = "Course Name cannot be Blank")
    private String courseName;

    @NotNull(message = "Semester cannot be Null")
    @Min(value = 1,message = "Minimum value of semester is 1")
    @Max(value = 8,message = "Maximum value of Semester is 8")
    private int semester;
    private String departmentName;
    @NotNull(message = "days cannot be Null")
    @Min(value = 1,message = "Minimum value of Days is 1")
    private int days;

    @NotNull(message = "TimeTable cannot be null")
    private List<Schedule> timeTable;

    @NotNull(message = "Course Log cannot be null")
    private List<Log> courseLog;

    //this stores  the lecture Room ID s belongs to this course
    private Set<LectureRoomRef> lectureRoomIDs = new HashSet<>();

    //this stores student ids belongs to this course
    private Set<String> studentsIds = new HashSet<>();

    private Set<String> lecturerIds = new HashSet<>();

    public String getCourseId() {
        return this.courseId;
    }

    public String getCourseNumber() {
        return this.courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public List<Schedule> getTimeTable() {
        return timeTable;
    }

    public void setTimeTable(List<Schedule> timeTable) {
        this.timeTable = timeTable;
    }

    public List<Log> getCourseLog() {
        return courseLog;
    }

    public void setCourseLog(List<Log> courseLog) {
        this.courseLog = courseLog;
    }

    public void addCourseLog(Log log) {
        this.courseLog.add(log);
    }

    public void addCourseSchedule(Schedule schedule) {
        this.timeTable.add(schedule);
    }

    public void addLectureRoom(LectureRoom lectureRoom){
        this.lectureRoomIDs.add(new LectureRoomRef(lectureRoom.getRoomId()));
    }

    public Set<String> getLectureRoomIDs(){
        return this.lectureRoomIDs.stream()
                .map(LectureRoomRef::getRoomId)
                .collect(Collectors.toSet());
    }

    public void addLectureRooms(HashSet<LectureRoomRef> set){
        this.lectureRoomIDs.addAll(set);
    }

    public void setLectureRoomIDs(Set<LectureRoomRef> lectureRoomIDs) {
        this.lectureRoomIDs = lectureRoomIDs;
    }

    public void removeLectureRoomID(LectureRoomRef lectureRoomRef,LectureRoom lectureRoom){
        //lecture room can be stored in a course at two places
        //1.in Schedule field  2.lecturerooms field
        //both will be removed and saved

//        System.out.println("prev = "+this.lectureRoomIDs);
        this.lectureRoomIDs.removeIf(lRef -> lRef.getRoomId().equals(lectureRoomRef.getRoomId()));
//        System.out.println("post = "+this.lectureRoomIDs);

        for(Schedule s :this.timeTable){
            if(s.getRoomName().equals(lectureRoom.getRoomName())){
                timeTable.remove(s);
                break;
            }
        }


    }

    public Set<String> getStudentsIds() {
        return studentsIds;
    }

    public void setStudentsIds(Set<String> studentsIds) {
        this.studentsIds = studentsIds;
    }

    public void addStudent(Optional<Student> student){
        student.ifPresent(s -> this.studentsIds.add(s.getStudentID()));
    }

    public void clearStudentSet(){
        this.studentsIds.clear();
    }

    public Set<String> getLecturerIds() {
        return lecturerIds;
    }

    public void setLecturerIds(Set<String> lecturerIds) {
        this.lecturerIds = lecturerIds;
    }

    public void addLecturer(Optional<Lecturer> lecturer){
        lecturer.ifPresent(l -> this.lecturerIds.add(l.getLectID()));
    }

    public void clearLecturerSet(){
        this.lecturerIds.clear();
    }

    public void removeLecturer(Optional<Lecturer> lecturer){
        lecturer.ifPresent(l -> this.lecturerIds.remove(l.getLectID()));
    }
}
