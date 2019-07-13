package pl.jbaranska.alpha.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import static javax.persistence.FetchType.EAGER;

@ToString
@Getter
@Setter
@Entity
@Table(name = "APP_USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "EMAIL", unique = true)
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "STREET")
    private String street;

    @Column(name = "CITY")
    private String city;

    @Column(name = "ZIP")
    private String zip;

    @Column(name = "PHONE")
    private String phone;

    @ManyToMany(fetch = EAGER)
    @JoinTable(name = "USERS_ROLES",
            joinColumns = @JoinColumn(name = "ID_USER"),
            inverseJoinColumns = @JoinColumn(name = "ID_ROLE"))
    private Set<Role> roles;

    public User(){}

    public User(String name, String surname, String email, String password, String street, String city, String zip, String phone, Set<Role> roles) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.phone = phone;
        this.roles = roles;
    }

    public void addRole(Role role){
        if (roles == null) {
            roles = new HashSet<>();
        }
        roles.add(role);
    }
}
