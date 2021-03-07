package Group10.example.API.Repository;

import Group10.example.API.Model.Course;
import Group10.example.API.Model.LectureRoomRef;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CourseRepository extends MongoRepository<Course, String> {
    Collection<Course> findBySemesterAndDepartmentName(int semester,String department);

    Optional<Course> findByCourseNumber(String courseNumber);

    List<Course> findByLectureRoomIDsContains(LectureRoomRef roomRef);

}
