package pl.jbaranska.alpha.services;

import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import pl.jbaranska.alpha.entity.Item;
import pl.jbaranska.alpha.models.Basket;
import pl.jbaranska.alpha.models.ItemForm;

import java.util.List;
import java.util.Optional;

@Service
@SessionScope
public class BasketServices {

    @Getter
    private Basket basket = new Basket();

    public void addProductToBasket(ItemForm newItem)
    {

        List<ItemForm> items = basket.getBasketItems();
        Optional<ItemForm> first = items.stream().filter(p -> p.getProductId().equals(newItem.getProductId())).findFirst();

        if(first.isPresent())
        {
            first.get().setQuantity(first.get().getQuantity() + 1 );
        }
        else{
            ItemForm item = new ItemForm();
            item.setProductId(newItem.getProductId());
            item.setProduct(newItem.getProduct());
            item.setQuantity(newItem.getQuantity());
            items.add( item);
        }

    }

    public Double getTotalPrice(){
        return this.basket.getBasketItems()
                .stream()
                .map(a -> a.getQuantity()*a.getProduct().getPrice())
                .mapToDouble(Double::doubleValue).sum();
    }
    /*
    public Double getTotalPrice()
    {
        return  this.basket.getBasketItems()
                .stream()
                .map(p -> p.getProduct().getPrice())
                .mapToDouble(Double::doubleValue).sum();
    }
*/
    public List<ItemForm> getBasket()
    {
        return basket.getBasketItems();
    }

    public void clearBasket()
    {
        this.basket.clearBasket();
    }

}
