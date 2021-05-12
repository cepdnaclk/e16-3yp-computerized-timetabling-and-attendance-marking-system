package Group10.example.API.Repository;
import Group10.example.API.Model.Student;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends MongoRepository<Student,String> {

    Student findByuserName(String userName);

    Optional<Student> findByRegNumber(String regNo);
}
