package pl.jbaranska.alpha.models;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductForm {

    private String product;
    private String size;
    private String categoryId;
    private Double price;

    public ProductForm(){}

    public ProductForm( String product, String size, String categoryId, Double price) {
        this.product = product;
        this.size = size;
        this.categoryId = categoryId;
        this.price = price;
    }
}
