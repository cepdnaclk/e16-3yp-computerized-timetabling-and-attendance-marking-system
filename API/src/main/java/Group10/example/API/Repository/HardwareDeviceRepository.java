package Group10.example.API.Repository;

import Group10.example.API.Model.HardwareDevice;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HardwareDeviceRepository  extends MongoRepository<HardwareDevice,String> {

    public HardwareDevice findByUserName(String userName);
}
