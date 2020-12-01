package Group10.example.API.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class ViewController {

    @GetMapping("/")
   public ModelAndView home() {
        ModelAndView mv = new ModelAndView("adminLogin");
        return mv;
    }

}
