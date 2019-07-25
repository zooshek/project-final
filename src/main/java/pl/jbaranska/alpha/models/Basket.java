package pl.jbaranska.alpha.models;

import lombok.Getter;
import lombok.Setter;

import lombok.ToString;
import org.assertj.core.util.Lists;
import pl.jbaranska.alpha.entity.Item;

import java.util.List;

@Setter
@ToString
public class Basket {

    private List<ItemForm> itemList = Lists.newArrayList();

    public List<ItemForm> getBasketItems() {
        return itemList;
    }

    public boolean isBasketEmpty()
    {
        return this.itemList.isEmpty();
    }

    public void clearBasket(){
        this.itemList.removeAll(itemList);
    }
}

