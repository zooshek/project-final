package pl.jbaranska.alpha.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.jbaranska.alpha.entity.Item;
import pl.jbaranska.alpha.models.PizzaForm;
import pl.jbaranska.alpha.models.ItemForm;
import pl.jbaranska.alpha.services.OrderServices;
import pl.jbaranska.alpha.services.ProductServices;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/menu")
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
        PizzaForm pizzaForm = new PizzaForm();
        productServices.showProductPizza().forEach(pizza -> {
            pizzaForm.getItems().add(new ItemForm(pizza.getId(), 0, pizza));
        });
        model.addAttribute("pizzaForm", pizzaForm);
        return "selectPizza";
    }

    @PostMapping("/pizza")
    public String getSelectedPizza(@ModelAttribute("pizzaForm") @Valid PizzaForm pizzaForm
            , BindingResult bindingResult
            , Model model){
        List<ItemForm> items = pizzaForm.getItems();
        for (ItemForm itemForm : items) {
            if (itemForm.getQuantity() > 0) {
                System.out.println(itemForm);
                orderServices.addProductToCart(new Item(itemForm.getProductId(), itemForm.getProduct().getPrice(), itemForm.getQuantity()));
            }
        }
        return "basket";
    }
}
