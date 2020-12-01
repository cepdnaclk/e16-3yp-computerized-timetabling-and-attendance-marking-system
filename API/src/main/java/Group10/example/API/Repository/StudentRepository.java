package Group10.example.API.Repository;
import Group10.example.API.Model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student,String> {

    public Student findByuserName(String userName);
}
