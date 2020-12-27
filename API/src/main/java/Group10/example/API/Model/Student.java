package Group10.example.API.Model;

import Group10.example.API.Exception.ValidPassword;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashSet;

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

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

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
