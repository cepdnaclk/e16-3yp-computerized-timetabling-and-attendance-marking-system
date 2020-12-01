package Group10.example.API.Repository;

import Group10.example.API.Model.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;

public interface CourseRepository extends MongoRepository<Course,String> {

    Collection<Course> findBySemester(int semester);
}
