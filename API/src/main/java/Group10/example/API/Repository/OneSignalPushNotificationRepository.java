package Group10.example.API.Repository;


import Group10.example.API.Model.OneSignalPushNotification;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Weslei Dias.
 */
public interface OneSignalPushNotificationRepository extends MongoRepository<OneSignalPushNotification, Long> {
}
