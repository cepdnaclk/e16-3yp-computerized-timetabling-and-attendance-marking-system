package Group10.example.API.Controller;

import Group10.example.API.ApiApplication;
import Group10.example.API.Model.Group;
import Group10.example.API.Model.Lecturer;
import Group10.example.API.Model.Student;
import Group10.example.API.Model.groupPayLoad;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GroupControllerIntTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void createGroup() {

        HashSet<String> lecList = new HashSet();
        lecList.add("1");
        lecList.add("2");

        HashSet<String> studentList = new HashSet();
        studentList.add("11");
        studentList.add("22");

        HashSet<String> courseList = new HashSet();
        courseList.add("em212");
        courseList.add("em213");

        Group group = new Group("em212-1","em212",studentList,lecList,courseList);

        ResponseEntity<Map> responseEntity = this.restTemplate
                .postForEntity("http://localhost:" + port + "/groups/create/", group, Map.class);
        System.out.println(responseEntity.getBody().toString());
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    void addLec() {

        List<String> lecIds = new ArrayList();
        lecIds.add("5fc449e50630fe2309f5f064");
        lecIds.add(("60111fde6191e5431666f93b"));

        groupPayLoad groupPayLoad = new groupPayLoad(lecIds,"60113cc18f75a7652390b18f");
        ResponseEntity<Map> responseEntity = this.restTemplate
                .postForEntity("http://localhost:" + port + "/groups/add/lecturers", groupPayLoad, Map.class);
        System.out.println(responseEntity.getBody().toString());
        assertEquals(200, responseEntity.getStatusCodeValue());

    }
}