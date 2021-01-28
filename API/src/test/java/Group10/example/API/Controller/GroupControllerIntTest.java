package Group10.example.API.Controller;

import Group10.example.API.ApiApplication;
import Group10.example.API.Model.Group;
import Group10.example.API.Model.GroupPayLoad;
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
}