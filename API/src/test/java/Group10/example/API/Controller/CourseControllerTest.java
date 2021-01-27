package Group10.example.API.Controller;

import Group10.example.API.ApiApplication;
import Group10.example.API.Model.Course;
import Group10.example.API.Model.Log;
import Group10.example.API.Model.Schedule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = ApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CourseControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void getCourses() {
        //get
        ResponseEntity<Course[]> responseEntity = this.restTemplate
                .getForEntity("http://localhost:" + port + "/courses/all",Course[].class);
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    void addCourse() {
        //post
        Course c = new Course();
        c.setCourseNumber("EM 208");
        c.setCourseName("Calculus I");
        c.setSemester(3);
        c.setDepartmentName("Engineering Mathematics");
        c.setDays(60);
        c.setTimeTable(new ArrayList<>());
        c.setCourseLog(new ArrayList<>());

        ResponseEntity<Course> responseEntity = this.restTemplate
                .postForEntity("http://localhost:" + port + "/courses/add", c, Course.class);
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    void addLogItem() {
        //put
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Log log = new Log("Wednesday 08:00:00-09:00:00","Wednesday 13:00:00-14:30:00","2021-09-06");
        HttpEntity<Log> requestUpdate = new HttpEntity<>(log, headers);
        ResponseEntity<Course> responseEntity = this.restTemplate.
                exchange("http://localhost:" + port + "/courses/addlogitem/6010c97804fafe334a7784ac", HttpMethod.PUT,requestUpdate,Course.class);
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    void addScheduleItem() {
        //put
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String date = "2021-01-23";
        String time1 = "10:00:00",time2 = "12:00:00";
        Schedule schedule = new Schedule(LocalDate.parse(date),"Wednesday",LocalTime.parse(time1),LocalTime.parse(time2),1,"Lecture Room 9");
        HttpEntity<Schedule> requestUpdate = new HttpEntity<>(schedule, headers);
        ResponseEntity<Course> responseEntity = this.restTemplate.
                exchange("http://localhost:" + port + "/courses/addscheduleitem/6010c97804fafe334a7784ac", HttpMethod.PUT,requestUpdate,Course.class);
        assertEquals(200, responseEntity.getStatusCodeValue());
    }
}