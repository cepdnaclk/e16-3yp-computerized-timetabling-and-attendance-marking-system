package Group10.example.API.Controller;
import Group10.example.API.ApiApplication;
import Group10.example.API.Model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest(classes = ApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsersControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testReg() throws Exception {
        Student stu = new Student();
        stu.setEmail("e16399@eng.pdn.ac.lk");
        stu.setFirstName("Erandana");
        stu.setLastName("Wijerathne");
        stu.setDepartment("com");
        stu.setSemester(1);
        stu.setYear(1);
        stu.setRegNumber("E/16/300");


        String uri = "/user/registration/student";

        //post req
        ResponseEntity<String> responseEntity = this.restTemplate
                .postForEntity(uri, stu,String.class);
               // assertEquals(200, responseEntity.getStatusCodeValue());

       String responseInJson = responseEntity.getBody();
       System.out.println(responseInJson);
    }

    @Test
    void getStudents(){
        String uri = "/user/all/students";

        //get
        ResponseEntity<String> responseEntity = this.restTemplate
                .getForEntity(uri,String.class);
        // assertEquals(200, responseEntity.getStatusCodeValue());
        String responseInJson = (String)responseEntity.getBody();
        System.out.println(responseInJson);
    }
    /**
     * this utility method Maps an Object into a JSON String. Uses a Jackson ObjectMapper.
     */
    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

    /**
     * This utility method to create the url for given uri. It appends the RANDOM_PORT generated port
     */
    private String formFullURLWithPort(String uri) {
        return "http://localhost:" + this.port + uri;
    }
}

