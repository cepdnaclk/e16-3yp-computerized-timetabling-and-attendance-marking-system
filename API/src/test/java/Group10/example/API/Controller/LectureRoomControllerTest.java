package Group10.example.API.Controller;

import Group10.example.API.ApiApplication;
import Group10.example.API.Model.LectureRoom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LectureRoomControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void addLectureRoom() {
        LectureRoom lr = new LectureRoom("test Room",34);
        ResponseEntity<LectureRoom> responseEntity = this.restTemplate
                .postForEntity("http://localhost:" + port + "/lecturerooms/add", lr, LectureRoom.class);
        assertEquals(200, responseEntity.getStatusCodeValue());
    }
}