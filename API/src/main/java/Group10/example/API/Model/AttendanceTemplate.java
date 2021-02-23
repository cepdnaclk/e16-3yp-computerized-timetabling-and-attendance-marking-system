package Group10.example.API.Model;

public class AttendanceTemplate {
    private Attendance attendance;
    private String name;
    private String regNo;

    public AttendanceTemplate(Attendance attendance, String name, String regNo) {
        this.attendance = attendance;
        this.name = name;
        this.regNo = regNo;
    }

    public Attendance getAttendance() {
        return attendance;
    }

    public String getName() {
        return name;
    }

    public String getRegNo() {
        return regNo;
    }
}
