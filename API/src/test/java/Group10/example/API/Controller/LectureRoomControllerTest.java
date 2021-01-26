package Group10.example.API.Controller;

import Group10.example.API.Model.LectureRoom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

class LectureRoomControllerTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    void addLectureRoom() {
        LectureRoom lectureRoom = new LectureRoom("test Room",67);

        HttpEntity<LectureRoom> entity = new HttpEntity<>(lectureRoom, headers);

        ResponseEntity<LectureRoom> response = restTemplate.exchange(
                createURLWithPort("/lecturerooms/add"),
                HttpMethod.POST, entity, LectureRoom.class);

        String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);

        assertTrue(actual.contains("/lecturerooms/add"));
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}