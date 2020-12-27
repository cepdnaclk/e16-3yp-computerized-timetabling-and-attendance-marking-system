package Group10.example.API.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController{

    @GetMapping("mobile/test")
    String index(){

        return "Hi";
    }
}
