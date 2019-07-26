package pl.jbaranska.alpha.controllers;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.jbaranska.alpha.entity.User;
import pl.jbaranska.alpha.models.UserForm;
import pl.jbaranska.alpha.models.UserFormRegistration;
import pl.jbaranska.alpha.services.UserServices;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.sql.SQLException;

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

    @GetMapping("user/{id}")
    public String  showUserForm(@PathVariable Integer id, Model model){
        UserForm userForm = new UserForm();
        if(userServices.getUserById(id).isPresent())
        {
            User user = userServices.getUserById(id).get();
            userForm.setName(user.getName());
            userForm.setSurname(user.getSurname());
            userForm.setStreet(user.getStreet());
            userForm.setCity(user.getCity());
            userForm.setZip(user.getZip());
            userForm.setPhone(user.getPhone());
            model.addAttribute("userForm", userForm);
            model.addAttribute("user", userServices.getUserById(id).get());
            return "userForm";
        }
        else {
            throw new EntityNotFoundException("User not found with id:\" + id");
        }
    }

    @GetMapping("user/edit/{id}")
    public String showEditUserForm(@PathVariable Integer id, Model model){

        if(userServices.getUserById(id).isPresent()) {
            model.addAttribute("userEditForm", new UserForm());
            model.addAttribute("user", userServices.getUserById(id).get());
            return "userEditForm";
        }
        else {
            throw new EntityNotFoundException("User not found with id:\" + id");
        }
    }

    @PostMapping("user/edit/{id}")
    public String  editUserAddress(@PathVariable Integer id, @ModelAttribute("userEditForm") @Valid UserForm userEditForm
            , BindingResult bindingResult, Model model){
            if(userServices.getUserById(id).isPresent()) {
                if(bindingResult.hasErrors()){
                    model.addAttribute("userEditForm", userEditForm);
                    model.addAttribute("user", userServices.getUserById(id).get());
                    return "userEditForm";
                }
                User user = userServices.getUserById(id).get();
                user.setName(userEditForm.getName());
                user.setSurname(userEditForm.getSurname());
                user.setStreet(userEditForm.getStreet());
                user.setCity(userEditForm.getCity());
                user.setZip(userEditForm.getZip());
                user.setPhone(userEditForm.getPhone());
                try{
                    userServices.editUser(user);
                }catch(SQLException e)
                {
                    bindingResult.reject("save-in-db-error", "Błąd przy zapisie do bazy");
                }
                model.addAttribute("userForm", userEditForm);
                model.addAttribute("user", userServices.getUserById(id).get());
                return "userForm";
            }
            else {
                throw new EntityNotFoundException("User not found with id:\" + id");
            }
    }


    @PostMapping("/login")
    public String showHomePage() {

        return "homePage";
    }
}
