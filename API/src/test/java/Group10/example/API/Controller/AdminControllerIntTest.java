package Group10.example.API.Controller;

import Group10.example.API.ApiApplication;
import Group10.example.API.Model.LectureRoom;
import Group10.example.API.Model.Lecturer;
import Group10.example.API.Model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = ApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AdminControllerIntTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void addNewStudent() {

        HashSet<String> courseSet = new HashSet<>();
        courseSet.add("CO321");
        courseSet.add("CO322");

        HashSet<String> groupSet = new HashSet<>();
        groupSet.add("111");
        groupSet.add("112");

        Student student = new Student("Saubhagya","Munasinghe","saubhagya","com","student","abc123","242",5,3,"sm201211d@gmail.com",courseSet,groupSet);
        ResponseEntity<Map> responseEntity = this.restTemplate
                .postForEntity("http://localhost:" + port + "/admn/create/studentacc", student, Map.class);
        System.out.println(responseEntity.getBody().toString());
        assertEquals(200, responseEntity.getStatusCodeValue());

    }

    @Test
    void addNewLecturer() {

        Lecturer lecturer = new Lecturer("Saubhagya222","Saubhagya","Munasinghe","e16399@eng.pdn.ac.lk","com");
        ResponseEntity<Map> responseEntity = this.restTemplate
                .postForEntity("http://localhost:" + port + "/admn/create/lectureracc", lecturer ,Map.class);
        System.out.println(responseEntity.getBody().toString());
        assertEquals(200, responseEntity.getStatusCodeValue());

    }
}