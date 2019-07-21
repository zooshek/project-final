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

import java.util.List;

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
       // model.addAttribute("pizzaDetails", productServices.showProductPizza());
     //   model.addAttribute("pizzaList", productServices.getPizzaList());
        PizzaForm pizzaForm = new PizzaForm();
        productServices.showProductPizza().forEach(pizza -> {
            pizzaForm.getItems().add(new ItemForm(pizza.getId(), 0, pizza));
        });
        model.addAttribute("pizzaForm", pizzaForm);
        return "selectPizza";
    }

    @PostMapping("/pizza")
    public String getSelectedPizza(@ModelAttribute("pizzaForm") PizzaForm pizzaForm, Model model){
        List<ItemForm> items = pizzaForm.getItems();
        for (ItemForm item : items)
        {
            if (item.getQuantity()>0) {

                item.getProductId();

            }
        }
    //    productServices.showProductPizza().forEach(pizza -> {
    //        pizzaForm.getItems().add(new ItemForm(pizza.getId(), 0, pizza));
     //   });
        model.addAttribute("pizzaForm", pizzaForm);
        return "basket";
    }
}
