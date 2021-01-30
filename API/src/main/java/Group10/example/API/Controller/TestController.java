package Group10.example.API.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController{

    @GetMapping("admin/hi")
    String index(){

        return "Hi";
    }

    @GetMapping("student/hi")
    String index1(){

        return "Hi";
    }

}
