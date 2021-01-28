package Group10.example.API.Controller;

import Group10.example.API.ApiApplication;
import Group10.example.API.Model.Attendance;
import Group10.example.API.Model.AttendanceItem;
import Group10.example.API.Model.Course;
import Group10.example.API.Model.CourseRegModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = ApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AttendanceControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("Course Registration")
    void registerCourses() {
        CourseRegModel crm = new CourseRegModel();
        crm.setStudentId("600a7bfdaed08a2da86311ff");
        crm.setCourseList(Arrays.asList("5fe1c2ffc5c344209e542445", "5fe1c836c5c344209e542447"));
        ResponseEntity<String> responseEntity = this.restTemplate
                .postForEntity("http://localhost:" + port + "/courses/registration/add", crm, String.class);
        System.out.println(responseEntity.toString());
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    @DisplayName("Attendance Finding By Student and Course Ids")
    void findAttendanceByStudentAndCourse() {
        String courseId = "5fe1c2ffc5c344209e542445",studentId = "5fc449b80630fe2309f5f062";
        String url = "http://localhost:" + port + "/attendance/findattendancebystudentidandcourseid?course="+courseId+"&student="+studentId;
        ResponseEntity<Attendance> responseEntity = this.restTemplate
                .getForEntity(url,Attendance.class);
        System.out.println(responseEntity.getBody().toString());
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    @DisplayName("Adding an Attendance Log")
    void addAttendanceLog() {
        AttendanceItem ai = new AttendanceItem();
        ai.setDate(LocalDate.parse("2021-01-23"));
        ai.setTime(LocalTime.parse("10:00:00"));
        ai.setLab_or_lecture(1);
        ai.setPresent(true);

        String courseId = "5fe1c2ffc5c344209e542445",studentId = "5fc449b80630fe2309f5f062";
        ResponseEntity<String> responseEntity = this.restTemplate
                .postForEntity("http://localhost:" + port + "/attendance/addlog?course="+courseId+"&student="+studentId, ai, String.class);
        System.out.println(responseEntity.toString());
        assertEquals(200, responseEntity.getStatusCodeValue());
    }
}