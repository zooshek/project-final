package pl.jbaranska.alpha.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString(exclude = {"password"})
public class UserFormRegistration {

    @NotNull(message = "Proszę podać imię")
    @NotBlank(message = "Proszę podać imię")
    private String name;

    @NotNull(message = "Proszę podać nazwisko")
    @NotBlank(message = "Proszę podać nazwisko")
    private String surname;

    @NotNull(message = "Proszę podać email")
    @NotBlank(message = "Proszę podać email")
    @Pattern(regexp = "[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-z]{2,3}", message = "Nieprawidłowy email.")
    private String email;

    @NotBlank(message = "Proszę podać hasło")
    @NotNull(message = "Proszę podać hasło")
    @Size(min = 6, max = 30, message = "Hasło musi się składac z przynajmniej 6 znaków")
    private String password;

    @NotNull(message = "Proszę podać ulicę")
    @NotBlank(message = "Proszę podać ulicę")
    private String street;

    @NotNull(message = "Proszę podać miasto")
    @NotBlank(message = "Proszę podać miasto")
    private String city;

    @NotNull(message = "Proszę podać kod pocztowy")
    @NotBlank(message = "Proszę podać kod pocztowy")
    @Size(min =5, max=6, message = "Kod powinien zawierać od 5 do 6 znaków")
    private String zip;

    @NotNull(message = "Proszę podać telefon")
    @NotBlank(message = "Proszę podać telefon")
    private String phone;

}
