package Group10.example.API.DAO;

import Group10.example.API.Model.Notification;
import Group10.example.API.Repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class NotificationDAO {

    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationDAO(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void addNotification(Notification notification) {
        notificationRepository.insert(notification);
    }

    public Collection<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public void deleteAllNotifications() {
        notificationRepository.deleteAll();
    }

    public Collection<Notification> findAllByDayOfWeek(String dayOfWeek) {
        return notificationRepository.findAllByDayOfWeek(dayOfWeek);
    }

    public Optional<Notification> deleteById(String id) {
        return deleteById(id);
    }

    public Optional<Notification> findById(String id) {
        return notificationRepository.findById(id);
    }
}
