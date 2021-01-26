package Group10.example.API.Repository;

import Group10.example.API.Model.Attendance;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;

public interface AttendanceRepository extends MongoRepository<Attendance, String> {

    Collection<Attendance> findByStudentId(String studentId);

    Collection<Attendance> findByCourseId(String courseId);

    Collection<Attendance> findByCourseIdAndStudentId(String courseId, String studentId);

    void removeAllByStudentId(String studentId);

    void removeAllByCourseId(String courseId);
}
