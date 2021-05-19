package Group10.example.API.Model;

import org.springframework.data.annotation.Id;

public class HardwareDevice {

    @Id
    private String deviceId;

    private String userName;
    private String password;


    public HardwareDevice(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
