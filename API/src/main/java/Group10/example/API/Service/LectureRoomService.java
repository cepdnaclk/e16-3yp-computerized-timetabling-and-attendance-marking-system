package Group10.example.API.Service;

import Group10.example.API.DAO.LectureRoomDAO;
import Group10.example.API.Model.CourseUpdatePayLoad;
import Group10.example.API.Model.LectureRoom;
import Group10.example.API.Model.LectureRoomUpdatePayLoad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class LectureRoomService {

    private LectureRoomDAO lectureRoomDAO;

    @Autowired
    public LectureRoomService(LectureRoomDAO lectureRoomDAO) {
        this.lectureRoomDAO = lectureRoomDAO;
    }

    public Collection<LectureRoom> getLectureRooms() {
        return lectureRoomDAO.getLectureRooms();
    }


    public LectureRoom addLectureRoom(LectureRoom lectureRoom) {
        return lectureRoomDAO.addLectureRoom(lectureRoom);
    }

    public Optional<LectureRoom> getLectureRoomById(String id) {
        return lectureRoomDAO.getLectureRoomById(id);
    }

    public Optional<LectureRoom> deleteLectureRoomById(String id) {
        return lectureRoomDAO.deleteLectureRoomById(id);
    }

    public Optional<LectureRoom> updateLectureRoomById(String id, LectureRoomUpdatePayLoad lectureRoomUpdatePayLoad) {
        return lectureRoomDAO.updateLectureRoomById(id,lectureRoomUpdatePayLoad);
    }
}
