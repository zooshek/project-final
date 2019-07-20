package pl.jbaranska.alpha.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderPizzaForm {
    private String pizzaName;
    private String size;
    private Integer quantity;

}
