package Group10.example.API.Model;

import javax.validation.constraints.NotNull;

public class LoginPayload {


    String username;

    String Password;

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
