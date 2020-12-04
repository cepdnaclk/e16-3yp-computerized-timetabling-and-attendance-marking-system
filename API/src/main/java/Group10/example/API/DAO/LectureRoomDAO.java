package Group10.example.API.DAO;

import Group10.example.API.Model.LectureRoom;
import Group10.example.API.Model.LectureRoomUpdatePayLoad;
import Group10.example.API.Repository.LectureRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class LectureRoomDAO {

    private LectureRoomRepository lectureRoomRepository;

    @Autowired
    public LectureRoomDAO(LectureRoomRepository lectureRoomRepository) {
        this.lectureRoomRepository = lectureRoomRepository;
    }

    public Collection<LectureRoom> getLectureRooms() {
        return lectureRoomRepository.findAll();
    }

    public LectureRoom addLectureRoom(LectureRoom lectureRoom) {
        return lectureRoomRepository.insert(lectureRoom);
    }

    public Optional<LectureRoom> getLectureRoomById(String id) {
        return lectureRoomRepository.findById(id);
    }

    public Optional<LectureRoom> deleteLectureRoomById(String id) {
        Optional<LectureRoom> lectureRoom = lectureRoomRepository.findById(id);
        lectureRoom.ifPresent(lr -> lectureRoomRepository.delete(lr));
        return lectureRoom;
    }

    public Optional<LectureRoom> updateLectureRoomById(String id, LectureRoomUpdatePayLoad lectureRoomUpdatePayLoad) {
        Optional<LectureRoom> lectureRoom = lectureRoomRepository.findById(id);
        lectureRoom.ifPresent(lr -> lr.setRoom_name(lectureRoomUpdatePayLoad.getRoom_name()));
        lectureRoom.ifPresent(lr -> lr.setDevice(lectureRoomUpdatePayLoad.getDevice()));
        lectureRoom.ifPresent(lr -> lectureRoomRepository.save(lr));
        return lectureRoom;
    }

    public Optional<LectureRoom> findByDevice(int device_id) {
        return lectureRoomRepository.findByDevice(device_id);
    }
}
