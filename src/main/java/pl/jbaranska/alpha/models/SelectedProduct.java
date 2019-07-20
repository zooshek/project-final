package pl.jbaranska.alpha.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.jbaranska.alpha.entity.Product;

@Getter
@Setter
@ToString
public class SelectedProduct {
private Integer productId;
private Integer quantity;

}
