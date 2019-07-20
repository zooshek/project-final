package pl.jbaranska.alpha.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.jbaranska.alpha.entity.Product;

import java.util.List;

@Getter
@Setter
@ToString
public class OrderPizzaForm {
    private Integer productId;
    private Integer quantity;



    public SelectedProduct addProduct(Integer productId, Integer quantity)
    {
        SelectedProduct selectedProduct = new SelectedProduct();
        selectedProduct.setProductId(productId);
        selectedProduct.setQuantity(quantity);
        return  selectedProduct;
    }
}
