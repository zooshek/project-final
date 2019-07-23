package pl.jbaranska.alpha.services;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jbaranska.alpha.entity.Item;
import pl.jbaranska.alpha.entity.Order;
import pl.jbaranska.alpha.entity.User;
import pl.jbaranska.alpha.models.Basket;
import pl.jbaranska.alpha.models.ItemForm;
import pl.jbaranska.alpha.repositories.ItemRepository;
import pl.jbaranska.alpha.repositories.OrderRepository;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Service
public class OrderServices
{
    OrderRepository orderRepository;
    ItemRepository itemRepository;

    public OrderServices(OrderRepository orderRepository, ItemRepository itemRepository) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
    }

    @Transactional
    public Order submitOrder(Order order, List<Item> items){
        Order o = orderRepository.save(order);
        itemRepository.saveAll(items);
        return o;
    }

    public List<Order> getUserOrders(User user)
    {
        return orderRepository.findAllByUser(user);
    }
    public Optional<Order> getOrder(Order order){
        return orderRepository.findOrderById(order.getId());
    }

}
