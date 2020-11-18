package Group10.example.API.Model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "LectureRoom")
public class LectureRoom {
    private String room_id;
    private String device_id;

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }
}
