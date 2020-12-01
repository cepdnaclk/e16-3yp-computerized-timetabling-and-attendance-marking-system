package Group10.example.API.Repository;

import Group10.example.API.Model.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdminRepository extends MongoRepository<Admin,String> {

    public Admin findByuserName(String userName);
}