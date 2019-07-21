package pl.jbaranska.alpha.models;

import lombok.*;
import pl.jbaranska.alpha.entity.Product;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ItemForm {
    private Integer productId;
    private Integer quantity;
    private Product product;
}
