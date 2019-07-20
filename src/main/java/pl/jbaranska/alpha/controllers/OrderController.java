package pl.jbaranska.alpha.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.jbaranska.alpha.models.OrderPizzaForm;
import pl.jbaranska.alpha.services.OrderServices;
import pl.jbaranska.alpha.services.ProductServices;

@RequestMapping("/order")
@Controller
public class OrderController {
    private ProductServices productServices;
    private OrderServices orderServices;

    public OrderController(ProductServices productServices, OrderServices orderServices) {
        this.productServices = productServices;
        this.orderServices = orderServices;
    }

    @GetMapping("/pizza")
    public String selectPizza(Model model){
        model.addAttribute("pizzaList", orderServices.getPizzaList());
       // model.addAttribute("orderPizzaForm" ,new OrderPizzaForm());
        return "selectPizza";
    }
}
