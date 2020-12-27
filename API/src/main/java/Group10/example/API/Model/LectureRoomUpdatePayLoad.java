package Group10.example.API.Model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LectureRoomUpdatePayLoad {

    @NotNull(message = "Room Name cannot Be Null")
    @NotBlank(message = "Room Name cannot Be Blank")
    private String roomName;

    private int device;

    public String getRoomName() {
        return roomName;
    }

    public int getDevice() {
        return device;
    }
}
