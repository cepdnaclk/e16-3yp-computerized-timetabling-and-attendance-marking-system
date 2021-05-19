package Group10.example.API.Controller;

import Group10.example.API.Model.HardwareDevice;
import Group10.example.API.Repository.HardwareDeviceRepository;
import Group10.example.API.Service.HardwareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hardware")
public class DeviceController {

    @Autowired
    HardwareService hardwareService;

    @Autowired
    HardwareDeviceRepository hardwareDeviceRepository;


    @PostMapping("/add/device")
    public Map<String, String> addNewDevice(@RequestBody HardwareDevice hardwareDevice){

        HashMap<String,String> message = new HashMap<String,String>();
        HardwareDevice tmpDevice = hardwareDeviceRepository.findByUserName(hardwareDevice.getUserName());
        HardwareDevice device = hardwareService.insertDevice(hardwareDevice);

        if(device != null) message.put("message","Device added successfully" );
        else message.put("message","Device adding failed" );

        return message;


    }

    @GetMapping("/get/all")
    public List<HardwareDevice> addNewDevice(){

        return hardwareDeviceRepository.findAll();


    }

}
