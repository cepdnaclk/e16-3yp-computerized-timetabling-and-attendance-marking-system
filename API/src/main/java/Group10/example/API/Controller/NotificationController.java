package Group10.example.API.Controller;

import Group10.example.API.Model.Notification;
import Group10.example.API.Service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping(value = "/add/all")
    public String addAllNotifications(){
        return notificationService.addAllNotifications();
    }

    @GetMapping(value = "/find/all")
    public Collection<Notification> getAllNotifications(){
        return notificationService.getAllNotifications();
    }

    @DeleteMapping(value = "/delete/all")
    public void deleteAllNotifications(){
        notificationService.deleteAllNotifications();
    }

    @PostMapping(value = "/refresh/all")
    public void refreshAll(){
        notificationService.refreshAll();
    }

    @GetMapping(value = "/findbyday/all")
    public Collection<Notification> findAllByDayOfWeek(){
        return notificationService.findAllByDayOfWeek();
    }

    @DeleteMapping(value = "/delete/{id}")
    public Optional<Notification> deleteById(@PathVariable("id")String id){
        return notificationService.deleteById(id);
    }

    @PostMapping(value = "/addbyscheduleid/{id}")
    public Optional<Notification> addByScheduleId(@PathVariable("id")String id){
        return notificationService.addByScheduleId(id);
    }

    @GetMapping(value = "/find/{id}")
    public Optional<Notification> findById(@PathVariable("id")String id){
        return notificationService.findById(id);
    }
}
