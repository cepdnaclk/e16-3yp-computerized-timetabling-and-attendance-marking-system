package Group10.example.API.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.*;
import java.util.*;
import java.util.stream.Collectors;

@Document(collection = "Course")
public class Course {

    @Id
    private String courseId;
    @NotNull(message = "courseNumber cannot be Null")
    @NotBlank(message = "courseNumber cannot be Empty")
    private String courseNumber;
    @NotNull(message = "courseName cannot be Null")
    @NotBlank(message = "courseName cannot be Empty")
    private String courseName;
    @NotNull(message = "Semester cannot be Null")
    @Min(value = 1,message = "Minimum value of semester must be 1")
    @Max(value = 8,message = "Maximum value of semester must be 8")
    private int semester;
    @NotNull(message = "days cannot be Null")
    @Min(value = 1,message = "minimum value of days must be 1")
    private int days;
    private List<Schedule> timeTable;
    private List<Log> courseLog;

    //this stores  the lecture Room ID s belongs to this course
    private Set<LectureRoomRef> lectureRoomIDs = new HashSet<>();

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
}
