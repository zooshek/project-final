package pl.jbaranska.alpha.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.jbaranska.alpha.models.PizzaForm;
import pl.jbaranska.alpha.models.ItemForm;
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
        model.addAttribute("pizzaDetails", productServices.showProductPizza());
        model.addAttribute("pizzaList", productServices.getPizzaList());
        PizzaForm pizzaForm = new PizzaForm();
        productServices.showProductPizza().forEach(pp -> {
            pizzaForm.getItems().add(new ItemForm(pp.getId(), 0, pp));
        });
        model.addAttribute("pizzaForm", pizzaForm);
        return "selectPizza";
    }

    @PostMapping("/pizza")
    public String getSelectedPizza(@ModelAttribute("pizzaForm") PizzaForm pizzaForm, Model model){
        System.out.println(pizzaForm);
        return "selectPizza";
    }
}
