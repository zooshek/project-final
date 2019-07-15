package pl.jbaranska.alpha.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.jbaranska.alpha.models.UserFormRegistration;

@RequestMapping("/")
@Controller
public class RegisterController {

    public RegisterController(){}

    @GetMapping("/register")
    public String registerUser(Model model){
        model.addAttribute("registerForm", new UserFormRegistration());
        return "registerForm";
    }


}
