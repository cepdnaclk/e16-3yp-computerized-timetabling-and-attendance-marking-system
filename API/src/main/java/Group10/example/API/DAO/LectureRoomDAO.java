package Group10.example.API.DAO;

import Group10.example.API.Model.Course;
import Group10.example.API.Model.LectureRoom;
import Group10.example.API.Model.LectureRoomRef;
import Group10.example.API.Model.LectureRoomUpdatePayLoad;
import Group10.example.API.Repository.CourseRepository;
import Group10.example.API.Repository.LectureRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class LectureRoomDAO {

    private LectureRoomRepository lectureRoomRepository;
    private CourseRepository courseRepository;

    @Autowired
    public LectureRoomDAO(LectureRoomRepository lectureRoomRepository, CourseRepository courseRepository) {
        this.lectureRoomRepository = lectureRoomRepository;
        this.courseRepository = courseRepository;
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
        lectureRoom.ifPresent(lr -> {
            LectureRoomRef lRef = new LectureRoomRef(lr.getRoomId());
            List<Course> contained_courses = courseRepository.findByLectureRoomIDsContains(lRef);
            for(Course c : contained_courses){
                c.removeLectureRoomID(lRef,lr);
                System.out.println(lRef.toString());
                courseRepository.save(c);
            }
        });
        lectureRoom.ifPresent(lr -> lectureRoomRepository.delete(lr));
        return lectureRoom;
    }

    public Optional<LectureRoom> updateLectureRoomById(String id, LectureRoomUpdatePayLoad lectureRoomUpdatePayLoad) {
        Optional<LectureRoom> lectureRoom = lectureRoomRepository.findById(id);
        lectureRoom.ifPresent(lr -> lr.setRoomName(lectureRoomUpdatePayLoad.getRoomName()));
        lectureRoom.ifPresent(lr -> lr.setDevice(lectureRoomUpdatePayLoad.getDevice()));
        lectureRoom.ifPresent(lr -> lectureRoomRepository.save(lr));
        return lectureRoom;
    }

    public Optional<LectureRoom> findByDevice(int device_id) {
        return lectureRoomRepository.findByDevice(device_id);
    }

    public Optional<LectureRoom> findByRoomName(String roomName) {
        return lectureRoomRepository.findByRoomName(roomName);
    }

}
