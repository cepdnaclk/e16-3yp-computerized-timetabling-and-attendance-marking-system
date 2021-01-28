package Group10.example.API.Controller;

import Group10.example.API.ApiApplication;
import Group10.example.API.Model.Group;
import Group10.example.API.Model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LecturerControllerIntTest {

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
        courseList.add("ee212");
        courseList.add("ee213");

        Group group = new Group("ee212-1","ee212",studentList,lecList,courseList);
        ResponseEntity<Map> responseEntity = this.restTemplate
                .postForEntity("http://localhost:" + port + "/lec/creategroup", group, Map.class);

        System.out.println(responseEntity.getBody().toString());
        assertEquals(200, responseEntity.getStatusCodeValue());


    }

    @Test
    void getStudents(){

            ResponseEntity<Student[]> responseEntity = this.restTemplate
                    .getForEntity("http://localhost:" + port + "/lec/all/students/60113cc18f75a7652390b18f",Student[].class);
            //System.out.println(responseEntity.getBody().toString());
            assertEquals(200, responseEntity.getStatusCodeValue());



    }


}