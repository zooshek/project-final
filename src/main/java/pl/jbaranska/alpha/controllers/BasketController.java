package pl.jbaranska.alpha.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.jbaranska.alpha.entity.Item;
import pl.jbaranska.alpha.models.ItemForm;
import pl.jbaranska.alpha.models.PizzaForm;
import pl.jbaranska.alpha.services.BasketServices;
import pl.jbaranska.alpha.services.ProductServices;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BasketController {

    private BasketServices basketServices;
    private ProductServices productServices;

    public BasketController(BasketServices basketServices, ProductServices productServices) {
        this.basketServices = basketServices;
        this.productServices = productServices;
    }

    @GetMapping("/basket")
    public String showBasket(Model model)
    {
        model.addAttribute("basket", basketServices.getBasket());
        return "basket";
    }

    @GetMapping("menu/pizza")
    public String selectPizza(Model model){
        PizzaForm pizzaForm = new PizzaForm();
        productServices.showProductPizza().forEach(pizza -> {
            pizzaForm.getItems().add(new ItemForm(pizza.getId(), 0, pizza));
        });
        model.addAttribute("pizzaForm", pizzaForm);
        return "selectPizza";
    }

    @PostMapping("menu/pizza")
    public String getSelectedPizza(@ModelAttribute("pizzaForm") @Valid PizzaForm pizzaForm
            , BindingResult bindingResult
            , Model model){
        List<ItemForm> items = pizzaForm.getItems();
        for (ItemForm itemForm : items) {
            if (itemForm.getQuantity() > 0) {
                System.out.println(itemForm);
                basketServices.addProductToBasket(new ItemForm(itemForm.getProductId(), itemForm.getQuantity(), itemForm.getProduct()));
            }
        }
        return "basket";
    }
}
