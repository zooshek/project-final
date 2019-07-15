package pl.jbaranska.alpha.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "password")
public class UserFormRegistration {

    private String name;
    private String surname;
    private String email;
    private String password;
    private String street;
    private String city;
    private String zip;
    private String phone;

    public UserFormRegistration() {
    }

    public UserFormRegistration(String name, String surname, String email, String password, String street, String city, String zip, String phone) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.phone = phone;
    }
}
