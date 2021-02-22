package Group10.example.API.Model;

import Group10.example.API.Exception.ValidPassword;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Document(collection = "Lecturer")
public class Lecturer {

    public Lecturer(@NotNull(message = "User Name is mandatory") String userName, @NotNull(message = "First Name is mandatory") String firstName, @NotNull(message = "Last Name is mandatory") String lastName, @Email(message = "Email should be valid") String email, @NotNull(message = "Department is mandatory") String department) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.department = department;
    }
    public Lecturer(){}

    @Id
    private String lectID;

    @NotNull(message = "User Name is mandatory")
    private String userName;

    //@NotNull(message = "password is mandatory")
    //@ValidPassword
    private String password;

    @NotNull(message = "First Name is mandatory")
    private String firstName;

    @NotNull(message = "Last Name is mandatory")
    private String lastName;

    private String role;
    
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Department is mandatory")
    private String department;

    private Set<String>  courseIds = new HashSet<>();

    public String getLectID() {
        return lectID;
    }

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setLectID(String lectID) {
        this.lectID = lectID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
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

    public Set<String> getCourseIds() {
        return courseIds;
    }

    public void setCourseIds(Set<String> courseIds) {
        this.courseIds = courseIds;
    }

    public void addCourse(Optional<Course> course){
        course.ifPresent(c -> this.courseIds.add(c.getCourseId()));
    }

    public void removeCourse(Optional<Course> course){
        course.ifPresent(c -> this.courseIds.remove(c.getCourseId()));
    }

    public void removeAllCourses(){
        this.courseIds.clear();
    }
}
