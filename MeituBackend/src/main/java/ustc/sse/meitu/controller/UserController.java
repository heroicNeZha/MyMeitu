package ustc.sse.meitu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/backend")
public class UserController {
    @RequestMapping("/home")
    public String Home(Model model) {
        return "home";
    }
}
