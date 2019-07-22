package pl.jbaranska.alpha.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.jbaranska.alpha.services.OrderServices;

@Controller
public class BasketController {

    private OrderServices orderServices;

    public BasketController(OrderServices orderServices) {
        this.orderServices = orderServices;
    }

    @GetMapping("/basket")
    public String showBasket(Model model)
    {
        model.addAttribute("basket",orderServices.getCart());
        return "basket";
    }
}
