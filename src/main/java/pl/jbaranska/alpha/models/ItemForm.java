package pl.jbaranska.alpha.models;

import lombok.*;
import pl.jbaranska.alpha.entity.Product;

import javax.validation.constraints.Min;

@Getter
@Setter
@ToString

public class ItemForm {
    private Integer productId;
    @Min(value = 0, message = "Niepoprawna liczba")
    private Integer quantity;
    private Product product;

    public ItemForm(){}
    public ItemForm(Integer productId, Integer quantity, Product product) {
        this.productId = productId;
        this.quantity = quantity;
        this.product = product;
    }
}
