package Group10.example.API.Controller;
import Group10.example.API.ApiApplication;
import Group10.example.API.Model.GroupPayLoad;
import Group10.example.API.Model.LoginPayload;
import Group10.example.API.Model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(
        classes = {ApiApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class GroupControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void addLec() {
        String uri = "/groups/add/lecturers";

        GroupPayLoad groupPayload = new GroupPayLoad();
        List<String> idList = new ArrayList<>();
        idList.add("60111fde6191e5431666f93b");
        groupPayload.setIdList(idList);
        groupPayload.setGroupId("5fe7213d6b2b4a20668582bf");

        //post req
        ResponseEntity<String> responseEntity = this.restTemplate.postForEntity(uri, groupPayload, String.class, new Object[0]);
        String responseInJson = (String)responseEntity.getBody();
        System.out.println(responseInJson);
    }

    @Test
    void deleteLec() {
        String uri = "/groups/remove/lecturers";

        GroupPayLoad groupPayload = new GroupPayLoad();
        List<String> idList = new ArrayList<>();
        idList.add("60111fde6191e5431666f93b");
        groupPayload.setIdList(idList);
        groupPayload.setGroupId("5fe7213d6b2b4a20668582bf");

        //post req
        ResponseEntity<String> responseEntity = this.restTemplate.postForEntity(uri, groupPayload, String.class, new Object[0]);
        String responseInJson = (String)responseEntity.getBody();
        System.out.println(responseInJson);
    }

    @Test
    void addStudents() {
        String uri = "/groups/add/students";

        GroupPayLoad groupPayload = new GroupPayLoad();
        List<String> idList = new ArrayList<>();
        idList.add("600a79080ced2426366482da");
        groupPayload.setIdList(idList);
        groupPayload.setGroupId("5fe7213d6b2b4a20668582bf");

        //post req
        ResponseEntity<String> responseEntity = this.restTemplate.postForEntity(uri, groupPayload, String.class, new Object[0]);
        String responseInJson = (String)responseEntity.getBody();
        System.out.println(responseInJson);
    }

    @Test
    void addCourse() {
        String uri = "/groups/add/courses";

        GroupPayLoad groupPayload = new GroupPayLoad();
        List<String> idList = new ArrayList<>();
        idList.add("5fe1c2ffc5c344209e542445");
        groupPayload.setIdList(idList);
        groupPayload.setGroupId("5fe7213d6b2b4a20668582bf");

        //post req
        ResponseEntity<String> responseEntity = this.restTemplate.postForEntity(uri, groupPayload, String.class, new Object[0]);
        String responseInJson = (String)responseEntity.getBody();
        System.out.println(responseInJson);
    }

    @Test
    void getLec(){
        String uri = "/groups/all/lecturers/5fe7213d6b2b4a20668582bf";

        //get
        ResponseEntity<String> responseEntity = this.restTemplate
                .getForEntity(uri,String.class);
       // assertEquals(200, responseEntity.getStatusCodeValue());
        String responseInJson = (String)responseEntity.getBody();
        System.out.println(responseInJson);
    }

    @Test
    void getStu(){
        String uri = "/groups/all/students/5fe7213d6b2b4a20668582bf";

        //get
        ResponseEntity<String> responseEntity = this.restTemplate
                .getForEntity(uri,String.class);
        // assertEquals(200, responseEntity.getStatusCodeValue());
        String responseInJson = (String)responseEntity.getBody();
        System.out.println(responseInJson);
    }

    @Test
    void deleteStu(){
        String uri = "/groups/remove/students";

        GroupPayLoad groupPayload = new GroupPayLoad();
        List<String> idList = new ArrayList<>();
        idList.add("600a79080ced2426366482da");
        groupPayload.setIdList(idList);
        groupPayload.setGroupId("5fe7213d6b2b4a20668582bf");

        //post req
        ResponseEntity<String> responseEntity = this.restTemplate.postForEntity(uri, groupPayload, String.class, new Object[0]);
        String responseInJson = (String)responseEntity.getBody();
        System.out.println(responseInJson);
    }

    @Test
    void getCourses(){
        String uri = "/groups/all/courses/5fe7213d6b2b4a20668582bf";

        //get
        ResponseEntity<String> responseEntity = this.restTemplate
                .getForEntity(uri,String.class);
        // assertEquals(200, responseEntity.getStatusCodeValue());
        String responseInJson = (String)responseEntity.getBody();
        System.out.println(responseInJson);
    }

    @Test
    void deleteCourse(){
        String uri = "/groups/remove/courses";
        GroupPayLoad groupPayload = new GroupPayLoad();
        List<String> idList = new ArrayList<>();
        idList.add("5fe1c2ffc5c344209e542445");
        groupPayload.setIdList(idList);
        groupPayload.setGroupId("5fe7213d6b2b4a20668582bf");

        //post req
        ResponseEntity<String> responseEntity = this.restTemplate.postForEntity(uri, groupPayload, String.class, new Object[0]);
        String responseInJson = (String)responseEntity.getBody();
        System.out.println(responseInJson);

    }

    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }



    private String formFullURLWithPort(String uri) {
        return "http://localhost:" + this.port + uri;
    }
}




