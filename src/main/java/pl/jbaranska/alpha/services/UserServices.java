package pl.jbaranska.alpha.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jbaranska.alpha.entity.Role;
import pl.jbaranska.alpha.entity.User;
import pl.jbaranska.alpha.models.UserFormRegistration;
import pl.jbaranska.alpha.repositories.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServices {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RoleServices roleServices;

    public UserServices(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleServices roleServices) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleServices = roleServices;
    }

    public void registerUser(UserFormRegistration userFormRegistration) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleServices.addDefaultUserRole());
        User user = new User();
        user.setName(userFormRegistration.getName());
        user.setSurname(userFormRegistration.getSurname());
        user.setEmail(userFormRegistration.getEmail());
        user.setPassword(passwordEncoder.encode(userFormRegistration.getPassword()));
        user.setCity(userFormRegistration.getCity());
        user.setZip(userFormRegistration.getZip());
        user.setPhone(userFormRegistration.getPhone());
        user.setRoles(roles);


    }

}
