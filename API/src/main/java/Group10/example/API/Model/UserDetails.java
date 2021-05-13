package Group10.example.API.Model;

public class UserDetails {

    private String userName;
    private String eNo;

    public UserDetails(String userName, String eNo) {
        this.userName = userName;
        this.eNo = eNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String geteNo() {
        return eNo;
    }

    public void seteNo(String eNo) {
        this.eNo = eNo;
    }
}
