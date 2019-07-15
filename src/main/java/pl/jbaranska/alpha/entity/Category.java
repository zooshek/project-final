package pl.jbaranska.alpha.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "CATEGORY")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "CATEGORY_NAME")
    private String category;

    @OneToMany
    @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID")
    private List<Product> productList;

    public Category(){};

    public Category(String category) {
        this.category = category;
    }
}
