package Group10.example.API.Model;

import javax.validation.constraints.NotNull;

public class StudentPayload {

    @NotNull(message = "User Name is mandatory")
    String userName;
    @NotNull(message = "Password is mandatory")
    String Password;
    @NotNull(message = "User is mandatory")
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
