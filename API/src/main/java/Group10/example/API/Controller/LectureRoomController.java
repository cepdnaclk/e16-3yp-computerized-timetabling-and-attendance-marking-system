package Group10.example.API.Controller;

import Group10.example.API.Model.LectureRoom;
import Group10.example.API.Model.LectureRoomUpdatePayLoad;
import Group10.example.API.Service.LectureRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/lecturerooms")
public class LectureRoomController {

    private final LectureRoomService lectureRoomService;

    @Autowired
    public LectureRoomController(LectureRoomService lectureRoomService) {
        this.lectureRoomService = lectureRoomService;
    }

    @GetMapping("/all")
    public Collection<LectureRoom> getLectureRooms(){
        return lectureRoomService.getLectureRooms();
    }

    @PostMapping("/add")
    public LectureRoom addLectureRoom(@RequestBody LectureRoom lectureRoom){
        return lectureRoomService.addLectureRoom(lectureRoom);
    }

    @GetMapping(value = "/find/{id}")
    public Optional<LectureRoom> getLectureRoomById(@PathVariable("id") String id){
        return lectureRoomService.getLectureRoomById(id);
    }

    @DeleteMapping(value = "/delete/{id}")
    public Optional<LectureRoom> deleteLectureRoomById(@PathVariable("id") String id){
        return lectureRoomService.deleteLectureRoomById(id);
    }

    @PutMapping(value = "/update/{id}")
    public Optional<LectureRoom> updateLectureRoomById(@PathVariable("id") String id,@RequestBody LectureRoomUpdatePayLoad lectureRoomUpdatePayLoad){
        return lectureRoomService.updateLectureRoomById(id,lectureRoomUpdatePayLoad);
    }

    @GetMapping(value = "/findbydevice/{id}")
    public Optional<LectureRoom> findByDevice(@PathVariable("id") int device_id){
        return lectureRoomService.findByDevice(device_id);
    }

    @GetMapping(value = "/findbyroomname/{roomName}")
    public Optional<LectureRoom> findByRoomName(@PathVariable("roomName") String roomName){
        return lectureRoomService.findByRoomName(roomName);
    }

}
