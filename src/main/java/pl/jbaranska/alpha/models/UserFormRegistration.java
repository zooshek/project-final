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
public class UserFormRegistration extends UserForm{

    @NotNull(message = "Proszę podać email")
    @NotBlank(message = "Proszę podać email")
    @Pattern(regexp = "[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-z]{2,3}", message = "Nieprawidłowy email.")
    private String email;

    @NotBlank(message = "Proszę podać hasło")
    @NotNull(message = "Proszę podać hasło")
    @Size(min = 6, max = 30, message = "Hasło musi się składac z przynajmniej 6 znaków")
    private String password;

}
