package pl.jbaranska.alpha.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.jbaranska.alpha.exeptions.EmailAlreadyExistsException;
import pl.jbaranska.alpha.models.UserFormRegistration;
import pl.jbaranska.alpha.services.UserServices;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RequestMapping("/")
@Controller
public class RegisterController {

    private UserServices userServices;

    public RegisterController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model)
    {
        model.addAttribute("registerForm", new UserFormRegistration());
        return "registerForm";
    }

    @PostMapping("/register")
    public String registerNewUser(@ModelAttribute("registerForm") @Valid UserFormRegistration registerForm
            , BindingResult bindingResult
            , Model model
            , RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()){
            return "registerForm";
        }
        try {
            userServices.registerUser(registerForm);
        } catch (EmailAlreadyExistsException e) {
            bindingResult.rejectValue("email", "email-duplicate", "duplikat emaila");
            return "registerForm";
        }

        redirectAttributes.addFlashAttribute("msg", "Dzięki za rejestrację!");
        return "redirect:/";
    }

}
