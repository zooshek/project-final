package pl.jbaranska.alpha.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.jbaranska.alpha.entity.Product;

import java.util.List;

@Getter
@Setter
@ToString
public class SelectedItems {
    private Integer productId;
    private Integer quantity;

    public SelectedItems(){}

    public SelectedItems(Integer productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}
