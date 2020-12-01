package Group10.example.API.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document(collection = "Student")
public class Student {

    @Id
    private String studentID;

    @NotNull(message = "User Name is mandatory")
    private String userName;

    private String role;


    @NotNull(message = "password is mandatory")
    private String password;

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
}
