package pl.jbaranska.alpha.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.jbaranska.alpha.entity.Item;
import pl.jbaranska.alpha.models.ItemForm;
import pl.jbaranska.alpha.services.BasketServices;
import pl.jbaranska.alpha.services.OrderServices;
import pl.jbaranska.alpha.services.ProductServices;
import pl.jbaranska.alpha.services.UserServices;

import javax.validation.Valid;
import java.util.List;

@Controller
public class OrderController {
    private ProductServices productServices;
    private BasketServices basketServices;
    private OrderServices orderServices;
    private UserServices userServices;

    public OrderController(ProductServices productServices, BasketServices basketServices, OrderServices orderServices, UserServices userServices) {
        this.productServices = productServices;
        this.basketServices = basketServices;
        this.orderServices = orderServices;
        this.userServices = userServices;
    }

    @GetMapping("/order")
    public String preparationOrder(Model model){
        model.addAttribute("basket", basketServices.getBasket());
        model.addAttribute("totalPrice", basketServices.getTotalPrice());
        model.addAttribute("user", userServices.getUser());
        return "orderForm";
    }
}
