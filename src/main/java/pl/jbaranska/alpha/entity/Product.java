package pl.jbaranska.alpha.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "PRODUCTS")
public class Product {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "PRODUCT_NAME")
    private String product;

    @Column(name = "PRODUCT_SIZE")
    private String size;

    @Column(name = "IS_CUSTOMIZE")
    private boolean isCustomize;

    @Column(name = "CATEGORY_ID")
    private Integer categoryId;


}
