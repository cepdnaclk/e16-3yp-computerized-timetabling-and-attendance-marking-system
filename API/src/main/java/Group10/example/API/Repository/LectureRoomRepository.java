package Group10.example.API.Repository;

import Group10.example.API.Model.LectureRoom;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;
import java.util.Optional;

public interface LectureRoomRepository extends MongoRepository<LectureRoom,String> {
    Optional<LectureRoom> findByDevice(int device);

    Optional<LectureRoom> findByRoomName(String roomName);

}
