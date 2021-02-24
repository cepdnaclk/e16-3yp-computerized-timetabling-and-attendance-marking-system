package Group10.example.API.Repository;

import Group10.example.API.Model.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;

public interface ScheduleRepository extends MongoRepository<Schedule,String> {
    Collection<Schedule> findByCourseId(String courseId);

    Collection<Schedule> findByLecturerId(String lectureId);

    Collection<Schedule> findByCourseIdAndDayOfWeek(String courseId,String dayOfWeek);

    void removeAllByCourseId(String courseId);

}
