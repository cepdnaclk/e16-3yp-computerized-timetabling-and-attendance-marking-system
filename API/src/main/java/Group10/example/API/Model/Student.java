package Group10.example.API.Model;

import Group10.example.API.Exception.ValidPassword;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

@Document(collection = "Student")
public class Student {

    @Id
    private String studentID;

    @NotNull(message = "User Name is mandatory")
    private String userName;

    private String role;

    @NotNull(message = "password is mandatory")
    @ValidPassword
    private String password;

    @NotNull(message = "Registartion Number is mandatory")
    private String regNumber;

    @NotNull(message = "semester is mandatory")
    @Range(min=1,max=8)
    private int semester;

    @NotNull(message = "year is mandatory")
    @Range(min=1,max=4)
    private int year;

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    private HashSet<String> courseSet = new HashSet<>();

    public String getStudentID() {
        return studentID;
    }

    public String getUserName() {
        return userName;
    }

    public String getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addCourse(Course course){
        this.courseSet.add(course.getCourseId());
    }

    public void addAllCourses(ArrayList<String> courses){
        this.courseSet.addAll(courses);
    }

    public void removeCourse(Optional<Course> course){
        course.ifPresent(c ->this.courseSet.remove(c.getCourseId()));
    }

    public void removeAllCourses(){
        this.courseSet.clear();
    }

    public HashSet<String> getCourseSet() {
        return courseSet;
    }
}
