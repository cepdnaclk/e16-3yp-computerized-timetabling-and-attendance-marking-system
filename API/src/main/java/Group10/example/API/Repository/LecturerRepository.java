package Group10.example.API.Repository;

import Group10.example.API.Model.Lecturer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LecturerRepository extends MongoRepository<Lecturer,String> {

    public Lecturer findByuserName(String userName);
}
