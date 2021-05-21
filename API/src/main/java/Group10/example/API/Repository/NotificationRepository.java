package Group10.example.API.Repository;


import Group10.example.API.Model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;

public interface NotificationRepository extends MongoRepository<Notification,String> {
    Collection<Notification> findAllByDayOfWeek(String dayOfWeek);

    Collection<Notification> findAllByDayOfWeekAndStartTimeGreaterThanOrderByStartTime(String dayOfWeek,String startTime);
}
