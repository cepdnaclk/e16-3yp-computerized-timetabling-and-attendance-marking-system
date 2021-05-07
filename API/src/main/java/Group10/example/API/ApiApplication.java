package Group10.example.API;

import Group10.example.API.Service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;


@SpringBootApplication
public class ApiApplication {

	//Constructor Injection
	private final NotificationService notificationService;

	@Autowired
	public ApiApplication(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	//in every 2 minutes period do this
	//check whether there is a must send notification by checking database
	//if so send it.
	@Scheduled(fixedRateString = "PT2M")
	void someJob(){
		System.out.println("Now time is : "+new Date());
	}

	//in every sunday 6.00pm do this
	//clearing notification Collection and re adding them.set status to false
	@Scheduled(cron = "0 0 18 * * SUN")
	void refreshNotifications(){
		notificationService.refreshAll();
	}

}

@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "scheduling.enabled",matchIfMissing = true)
class SchedulingConfiguration {

}
