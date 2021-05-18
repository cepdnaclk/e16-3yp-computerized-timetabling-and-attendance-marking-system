package Group10.example.API;

import Group10.example.API.Controller.PushNotificationOptions;
import Group10.example.API.Model.Notification;
import Group10.example.API.Service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.TimeZone;

import static java.time.temporal.ChronoUnit.MINUTES;


@SpringBootApplication
public class ApiApplication {

	private final long NOTIFICATION_REMIND_TIME_IN_MINUTES = -10;

	//Constructor Injection
	private final NotificationService notificationService;

	@Autowired
	public ApiApplication(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	//in every 2 hours period do this
	//check whether there is a must send notification by checking database
	//if so send it.
	@Scheduled(fixedRateString = "PT120M")
	void someJob(){
//		String s = "Testing message.\nNow time is : "+new Date();
////		System.out.println(s);
//		PushNotificationOptions.sendMessageToAllUsers(s);
//
		//get all schedules in current dayOfWeek
		Collection<Notification> notifications = notificationService.findAllByDayOfWeekAndTime();
		for(Notification notification : notifications){
//			System.out.println(notification.toString());
//			System.out.println(!notification.getStatus());
////			System.out.println(check(notification.getStartTime()));
//			//check for already not sent notifications , also check for time difference < TIME_CONSTANT
//			if(!notification.getStatus() && check(notification.getStartTime())){
//				//send push notification
//				PushNotificationOptions.sendMessageToAllUsers(notification.getMessage());
//				//mark the notification as sent
//				notificationService.setStatus(notification.getNotificationId());
//			}
			if(checkForDayTime()){
				PushNotificationOptions.sendMessageToAllUsers(notification.getMessage());
				notificationService.setStatus(notification.getNotificationId());
				break;
			}
		}

	}

	//in every sunday 6.00pm do this
	//clearing notification Collection and re adding them.set status to false
	@Scheduled(cron = "0 0 18 * * SUN")
	void refreshNotifications(){
		notificationService.refreshAll();
	}

	boolean check(String startTime){
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("HH:mm");
		LocalDateTime localNow = LocalDateTime.now(TimeZone.getTimeZone("Asia/Colombo").toZoneId());
		String str_current = localNow.format(dateFormat);

		System.out.println("current = "+str_current);

		LocalTime lectureTime = LocalTime.parse(startTime, dateFormat);
		LocalTime current = LocalTime.parse(str_current, dateFormat);

		//get time difference in minutes
		long diff = MINUTES.between(lectureTime, current);
//		System.out.println(diff);
		if(diff < 0 && diff >= this.NOTIFICATION_REMIND_TIME_IN_MINUTES){
			return true;
		}
		return false;
	}

	boolean checkForDayTime(){
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("HH:mm");
		LocalDateTime localNow = LocalDateTime.now(TimeZone.getTimeZone("Asia/Colombo").toZoneId());
		String str_current = localNow.format(dateFormat);
		if(str_current.compareTo("06:00") >= 0 && str_current.compareTo("17.00") <= 0){
			return true;
		}
		return false;
	}


}

@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "scheduling.enabled",matchIfMissing = true)
class SchedulingConfiguration {

}