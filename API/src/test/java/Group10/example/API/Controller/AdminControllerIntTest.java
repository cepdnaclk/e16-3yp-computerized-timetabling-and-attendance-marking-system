package Group10.example.API.Controller;

import Group10.example.API.ApiApplication;
import Group10.example.API.Model.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = ApiApplication.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
public class AdminControllerIntTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void addNewStudent() {

        HashSet<String> courseSet = new HashSet();
        courseSet.add("CO321");
        courseSet.add("CO322");
        courseSet.add("CO323");

        HashSet<String> groupSet = new HashSet();
        groupSet.add("111");
        groupSet.add("112");
        groupSet.add("113");

        Student student = new Student("Saubhagya","Munasinghe","Saubhagya","com","student","abc123","242",5,3,"sm20121d@gmail.com",courseSet,groupSet);
        ResponseEntity<Student> responseEntity = this.restTemplate
                .postForEntity("http://localhost:" + port + "/create/studentacc", student, Student.class);
        System.out.println(responseEntity.getBody().toString());
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testAddNewStudent() {
    }
}