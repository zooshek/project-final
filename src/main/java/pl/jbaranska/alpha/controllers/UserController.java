package pl.jbaranska.alpha.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.jbaranska.alpha.models.UserFormRegistration;
import pl.jbaranska.alpha.services.UserServices;

@Controller
@RequestMapping("/")
public class UserController {

    private UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping("/login")
    public String showLoginForm() {

        return "loginForm";
    }

    @PostMapping("/login")
    public String showHomePage() {

        return "homePage";
    }
}
