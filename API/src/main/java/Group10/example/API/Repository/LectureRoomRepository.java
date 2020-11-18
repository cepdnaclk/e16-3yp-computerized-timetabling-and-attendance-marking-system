package Group10.example.API.Repository;

import Group10.example.API.Model.LectureRoom;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LectureRoomRepository extends MongoRepository<LectureRoom,String> {
}
