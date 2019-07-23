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
import pl.jbaranska.alpha.services.ProductServices;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/menu")
@Controller
public class OrderController {
    private ProductServices productServices;
    private BasketServices basketServices;

    public OrderController(ProductServices productServices, BasketServices basketServices) {
        this.productServices = productServices;
        this.basketServices = basketServices;
    }

}
