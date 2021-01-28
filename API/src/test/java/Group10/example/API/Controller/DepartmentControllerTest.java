package Group10.example.API.Controller;

import Group10.example.API.ApiApplication;
import Group10.example.API.Model.Department;
import Group10.example.API.Model.LectureRoom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DepartmentControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("Adding a Department")
    void addDepartment() {
        Department d = new Department();
        d.setDepartmentName("Production Engineering");
        ResponseEntity<Department> responseEntity = this.restTemplate
                .postForEntity("http://localhost:" + port + "/department/add", d, Department.class);
        assertEquals(200, responseEntity.getStatusCodeValue());
    }
}