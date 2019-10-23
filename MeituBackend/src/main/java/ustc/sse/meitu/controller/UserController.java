package ustc.sse.meitu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ustc.sse.meitu.pojo.User;
import ustc.sse.meitu.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/home")
    public String Home(Model model) {
        return "home";
    }

    @RequestMapping("/add")
    public String add(User user) {
        if (user != null && user.getUname() != null)
            userService.add(user);
        return "home";
    }

    public String login(User user) {
        if (user != null && user.getUusername() != null && user.getUpassword() != null) {
            userService.login(user);
        }
        return "";
    }
}
