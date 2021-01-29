package Group10.example.API.Service;

import Group10.example.API.ApiApplication;
import Group10.example.API.Model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application.properties")
class AdminServiceIntTest {

    @Autowired
    AdminService adminService;

    @Test
    void insertStudent() {

        HashSet<String> courseSet = new HashSet();
        courseSet.add("CO321");
        courseSet.add("CO322");
        courseSet.add("CO323");

        HashSet<String> groupSet = new HashSet();
        groupSet.add("111");
        groupSet.add("112");
        groupSet.add("113");

        Student student = new Student("Saubhagya","Munasinghe","Saubhagya","com","student","abc123","242",5,3,"sm20121d@gmail.com",courseSet,groupSet);
        adminService.insertStudent(student);




    }
}