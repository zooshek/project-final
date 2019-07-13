package pl.jbaranska.alpha.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter

@Entity
@ToString
@Table(name = "ROLES")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;

    @Column(name ="ROLE", unique = true)
    private String role;

    public Role(){}

    public Role(String role) {
        this.role = role;
    }
}