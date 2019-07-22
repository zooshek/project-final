package pl.jbaranska.alpha.services;

import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import pl.jbaranska.alpha.entity.Category;
import pl.jbaranska.alpha.entity.Item;
import pl.jbaranska.alpha.entity.Product;
import pl.jbaranska.alpha.models.Cart;
import pl.jbaranska.alpha.repositories.CategoryRepository;
import pl.jbaranska.alpha.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@SessionScope
public class OrderServices {

    @Getter
    private Cart cart = new Cart();

    public void addProductToCart(Item newItem)
    {

        List<Item> items = cart.getItemList();
        Optional<Item> first = items.stream().filter(p -> p.getIdProduct().equals(newItem.getIdProduct())).findFirst();

        if(first.isPresent())
        {
            first.get().setQuantity(first.get().getQuantity() + 1 );
        }
        else{
            Item item = new Item();
            item.setIdProduct(newItem.getIdProduct());
            item.setPrice(newItem.getPrice());
            item.setQuantity(newItem.getQuantity());
            items.add( item);
        }

        System.out.println(cart.getItemList());
    }

    public List<Item> getCart()
    {
        return cart.getItemList();
    }

}
