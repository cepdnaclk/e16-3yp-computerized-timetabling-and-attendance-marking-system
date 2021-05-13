package Group10.example.API.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Document(collection = "LectureRoom")
public class LectureRoom {
    @Id
    private String roomId;

    @NotNull(message = "Room Name cannot Be Null")
    @NotBlank(message = "Room Name cannot Be Blank")
    private String roomName;

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

    @Override
    public String toString() {
        return "LectureRoom{" +
                "roomId='" + roomId + '\'' +
                ", roomName='" + roomName + '\'' +
                ", device=" + device +
                '}';
    }

    public LectureRoom(@NotNull(message = "Room Name cannot Be Null") @NotBlank(message = "Room Name cannot Be Blank") String roomName, int device) {
        this.roomName = roomName;
        this.device = device;
    }
}
