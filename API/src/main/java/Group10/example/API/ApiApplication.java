package Group10.example.API;

import Group10.example.API.Service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;


@SpringBootApplication
public class ApiApplication {


	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}


}