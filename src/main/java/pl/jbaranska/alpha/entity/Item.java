package pl.jbaranska.alpha.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@ToString
@Entity
@Table(name = "ITEMS")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "PRODUCT_ID")
    private Integer idProduct;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID", referencedColumnName = "ID")
    private Order order;

    @Column(name = "PRICE")
    private Double price;

    @Column(name = "QUANTITY")
    private Integer quantity;

    public Item(){}


    public Item(Integer idProduct, Double price, Integer quantity) {
        this.idProduct = idProduct;
        this.price = price;
        this.quantity = quantity;
    }

    public Item(Integer idProduct, Order order, Double price, Integer quantity) {
        this.idProduct = idProduct;
        this.order = order;
        this.price = price;
        this.quantity = quantity;
    }
}
