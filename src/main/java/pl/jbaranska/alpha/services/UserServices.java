package pl.jbaranska.alpha.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.jbaranska.alpha.entity.User;
import pl.jbaranska.alpha.models.UserFormRegistration;
import pl.jbaranska.alpha.repositories.UserRepository;

@Service
public class UserServices {

    UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserServices(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public void registerUser(UserFormRegistration userFormRegistration)
    {
        User user = new User();
        user.setName(userFormRegistration.getName());
        user.setSurname(userFormRegistration.getSurname());
        user.setEmail(userFormRegistration.getEmail());
        user.setPassword(passwordEncoder.encode( userFormRegistration.getPassword()));
        user.setCity(userFormRegistration.getCity());
        user.setZip(userFormRegistration.getZip());
        user.setPhone(userFormRegistration.getPhone());

    }

}
