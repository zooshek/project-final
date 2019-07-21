package pl.jbaranska.alpha.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@ToString
@Getter
@Setter
@Entity
@Table(name = "PRODUCTS")
public class Order {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name ="USER_ID", referencedColumnName="ID")
    private User user;

    @Column (name = "ORDER_DATE")
    private Date orderDate;

    @Column(name = "TOTAL_PRICE")
    private Double totalPrice;

    public Order(){}

    public Order(User user, Date orderDate, Double totalPrice) {
        this.user = user;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
    }
}
