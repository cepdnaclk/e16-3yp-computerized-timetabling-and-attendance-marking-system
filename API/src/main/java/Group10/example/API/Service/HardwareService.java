package Group10.example.API.Service;

import Group10.example.API.Model.HardwareDevice;
import Group10.example.API.Repository.HardwareDeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class HardwareService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    HardwareDeviceRepository hardwareDeviceRepository;

    public HardwareDevice insertDevice(HardwareDevice hardwareDevice) {

        hardwareDevice.setPassword(passwordEncoder.encode(hardwareDevice.getPassword()));

        return hardwareDeviceRepository.insert(hardwareDevice);

    }

    public void deleteDevice(String id) {

        hardwareDeviceRepository.deleteById(id);

    }

}
