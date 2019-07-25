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
import pl.jbaranska.alpha.entity.Order;
import pl.jbaranska.alpha.entity.User;
import pl.jbaranska.alpha.models.ItemForm;
import pl.jbaranska.alpha.services.BasketServices;
import pl.jbaranska.alpha.services.OrderServices;
import pl.jbaranska.alpha.services.ProductServices;
import pl.jbaranska.alpha.services.UserServices;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
public class OrderController {
    private BasketServices basketServices;
    private OrderServices orderServices;
    private UserServices userServices;

    public OrderController(BasketServices basketServices, OrderServices orderServices, UserServices userServices) {
        this.basketServices = basketServices;
        this.orderServices = orderServices;
        this.userServices = userServices;
    }

    @GetMapping("/order")
    public String preparationOrder(Model model, RedirectAttributes redirectAttributes) {
        List<ItemForm> linesOrder = basketServices.getBasket();
        if(userServices.isAdmin())
        {
            throw new RuntimeException("Administrator nie może składać zamówień");
        }
        if (!linesOrder.isEmpty()) {
            Order order = new Order();
            if (!userServices.getUser().isPresent())
            {
                return "redirect:/login";
            } else {
                order.setUser(userServices.getUser().get());
            }
            order.setOrderDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
            order.setTotalPrice(basketServices.getTotalPrice());
            System.out.println(order);
            List<Item> items = new ArrayList<>();

            for (ItemForm lineOrder : linesOrder) {
                Item item = new Item();
                item.setIdProduct(lineOrder.getProductId());
                item.setOrder(order);
                item.setPrice(lineOrder.getProduct().getPrice());
                item.setQuantity(lineOrder.getQuantity());
                items.add(item);
                System.out.println(item);
            }
            Order newOrder = orderServices.submitOrder(order, items);
            basketServices.clearBasket();
            model.addAttribute("order", newOrder);
            return "orderForm";
        } else return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String showOrders(Model model,RedirectAttributes redirectAttributes){

        if (userServices.isAdmin())
        {
            model.addAttribute("orders", orderServices.getAllOrders());
            return "orders";
        }

        if(userServices.getUser().isPresent()) {

            model.addAttribute("orders", orderServices.getUserOrders(userServices.getUser().get()));
            return "orders";
        }
        else{
            model.addAttribute("orders",new ArrayList<>());
            return "orders";
        }

    }
}
