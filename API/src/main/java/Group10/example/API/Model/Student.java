package Group10.example.API.Model;

import Group10.example.API.Exception.ValidPassword;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Optional;

@Document(collection = "Student")
public class Student {
    public Student( @NotNull(message = "First Name is mandatory") String firstName, @NotNull(message = "Last Name is mandatory") String lastName, String userName, @NotNull(message = "Department is mandatory") String department, String role, String password, @NotNull(message = "Registartion Number is mandatory") String regNumber, @NotNull(message = "semester is mandatory") @Range(min = 1, max = 8) int semester, @NotNull(message = "year is mandatory") @Range(min = 1, max = 4) int year, @Email(message = "Email should be valid") String email, HashSet<String> courseSet, HashSet<String> groupSet) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.department = department;
        this.role = role;
        this.password = password;
        this.regNumber = regNumber;
        this.semester = semester;
        this.year = year;
        this.email = email;
        this.courseSet = courseSet;
        this.groupSet = groupSet;
    }

    public Student(){}

    @Id
    private String studentID;

    @NotNull(message = "First Name is mandatory")
    private String firstName;

    @NotNull(message = "Last Name is mandatory")
    private String lastName;


    private String userName;

    @NotNull(message = "Department is mandatory")
    private String department;

    private String role;

   // @NotNull(message = "password is mandatory")
   // @ValidPassword
    private String password;

    @NotNull(message = "Registartion Number is mandatory")
    private String regNumber;

    @NotNull(message = "semester is mandatory")
    @Range(min=1,max=8)
    private int semester;

    @NotNull(message = "year is mandatory")
    @Range(min=1,max=4)
    private int year;

    @Email(message = "Email should be valid")
    private String email;

    private HashSet<String> courseSet = new HashSet<>();

    private HashSet<String> groupSet = new HashSet<>();

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCourseSet(HashSet<String> courseSet) {
        this.courseSet = courseSet;
    }

    public HashSet<String> getGroupSet() {
        return groupSet;
    }

    public void setGroupSet(HashSet<String> groupSet) {
        this.groupSet = groupSet;
    }

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


    public String getStudentID() {
        return studentID;
    }

    public String getRole() {
        return role;
    }

    public String getPassword() {
        return password;
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
