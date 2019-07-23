package pl.jbaranska.alpha.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.jbaranska.alpha.entity.User;
import pl.jbaranska.alpha.models.ItemForm;
import pl.jbaranska.alpha.models.MenuForm;
import pl.jbaranska.alpha.services.BasketServices;
import pl.jbaranska.alpha.services.CategoryServices;
import pl.jbaranska.alpha.services.ProductServices;
import pl.jbaranska.alpha.services.UserServices;

import javax.validation.Valid;
import java.util.List;

@Controller
public class BasketController {

    private BasketServices basketServices;
    private ProductServices productServices;
    private CategoryServices categoryServices;
    private UserServices userServices;

    public BasketController(BasketServices basketServices, ProductServices productServices, CategoryServices categoryServices, UserServices userServices) {
        this.basketServices = basketServices;
        this.productServices = productServices;
        this.categoryServices = categoryServices;
        this.userServices = userServices;
    }

    @GetMapping("menu/{category}")
    public String selectPizza(@PathVariable String category, Model model){
        MenuForm menuForm = new MenuForm();
        productServices.getProductsByCategoryName(category)
                .forEach(o -> { menuForm.getItems().add(new ItemForm(o.getId(), 0, o));
            });
            model.addAttribute("menuForm", menuForm);
        return "selectMenu";
    }

    @PostMapping("menu/{category}")
    public String getSelectedPizza(@PathVariable String category, @ModelAttribute("menuForm") @Valid MenuForm menuForm
            , BindingResult bindingResult
            , Model model
            , RedirectAttributes redirectAttributes){
        List<ItemForm> items = menuForm.getItems();
        for (ItemForm itemForm : items) {
            /*
            if (itemForm.getQuantity()< 0){
                bindingResult.rejectValue("itemForm.quantity", "wrong-number", "Niepoprawna liczba");
                return "redirect:/menu/{category}";
            }
             */
            if (itemForm.getQuantity() > 0) {
                basketServices.addProductToBasket(new ItemForm(itemForm.getProductId(), itemForm.getQuantity(), itemForm.getProduct()));
            }
        }
        return "redirect:/basket";
    }

    @GetMapping("/basket")
    public String showBasket(Model model)
    {
        model.addAttribute("categories", categoryServices.showCategoryList());
        model.addAttribute("totalPrice", basketServices.getTotalPrice());
        model.addAttribute("basket", basketServices.getBasket());
        model.addAttribute("user",userServices.getUser().orElse(new User()) );
        return "basket";
    }
}
