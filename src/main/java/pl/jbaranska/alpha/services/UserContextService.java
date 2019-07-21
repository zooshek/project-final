package pl.jbaranska.alpha.services;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import pl.jbaranska.alpha.entity.Item;
import pl.jbaranska.alpha.entity.Product;
import pl.jbaranska.alpha.models.Cart;
import pl.jbaranska.alpha.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@SessionScope
public class UserContextService {

    @Getter
    private Cart cart = new Cart();

    public void addProductToCart(Product product){
        List<Item> items = cart.getItemList();
        Optional<Item> first = items.stream().filter(e -> e.getIdProduct().equals(product.getId())).findFirst();
        if (first.isPresent()) {
            first.get().setQuantity(first.get().getQuantity() + 1);
        } else {
            Item item = new Item();
            item.setIdProduct(product.getId());
            item.setPrice(product.getPrice());
            item.setQuantity(1);
            items.add(item);
        }

    }


}
