package Group10.example.API.Model;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "LectureRoom")
public class LectureRoom {
    @Id
    private String roomId;
    @NotNull(message = "Lecture Room Name cannot be Null")
    @NotBlank(message = "Lecture Room Name cannot be Blank")
    @Indexed(unique = true)
    private String roomName;
    @Indexed(unique = true)
    private int device;

    public String getRoomId() {
        return roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getDevice() {
        return device;
    }

    public void setDevice(int device) {
        this.device = device;
    }

}
