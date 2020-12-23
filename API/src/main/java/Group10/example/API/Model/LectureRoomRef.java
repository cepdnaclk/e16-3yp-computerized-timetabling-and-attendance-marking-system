package Group10.example.API.Model;


public class LectureRoomRef {
    private String roomId;

    public LectureRoomRef(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomId() {
        return roomId;
    }

    @Override
    public String toString() {
        return "LectureRoomRef{" +
                "roomId='" + roomId + '\'' +
                '}';
    }
}
