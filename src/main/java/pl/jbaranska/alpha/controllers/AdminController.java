package pl.jbaranska.alpha.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.jbaranska.alpha.models.UserFormRegistration;
import pl.jbaranska.alpha.services.RoleServices;
import pl.jbaranska.alpha.services.UserServices;

@Controller
@RequestMapping("/admin")
public class AdminController {
    UserServices userServices;
    RoleServices roleServices;

    public AdminController(UserServices userServices, RoleServices roleServices) {
        this.userServices = userServices;
        this.roleServices = roleServices;
    }

    @GetMapping("admin/users")
    public String showFormUser(Model model) {
        //model.addAttribute("userForm", new UserFormRegistration());
        //model.addAttribute("roles", roleServices.getRoles());
        return "userForm";
    }

    @PostMapping("admin/users")
    public String getUserDetails(Model model) {

        return "homePage";
    }
}
