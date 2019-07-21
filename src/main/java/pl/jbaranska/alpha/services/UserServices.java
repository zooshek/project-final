package pl.jbaranska.alpha.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jbaranska.alpha.entity.Role;
import pl.jbaranska.alpha.entity.User;
import pl.jbaranska.alpha.exeptions.EmailAlreadyExistsException;
import pl.jbaranska.alpha.models.UserFormRegistration;
import pl.jbaranska.alpha.repositories.RoleRepository;
import pl.jbaranska.alpha.repositories.UserRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServices {
    public static final String ROLE_USER = "ROLE_USER";
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;

    public UserServices(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Transactional(rollbackFor = {EmailAlreadyExistsException.class})
    public void registerUser(UserFormRegistration userFormRegistration) throws EmailAlreadyExistsException {

        if (userRepository.findFirstByEmail(userFormRegistration.getEmail()).isPresent()) {

            throw new EmailAlreadyExistsException("Duplicate email");
        }

        Role defaultRole = roleRepository.findByRole(ROLE_USER)
                .orElseGet(() -> roleRepository.save(new Role(ROLE_USER)));

        User user = new User();
        user.setName(userFormRegistration.getName());
        user.setSurname(userFormRegistration.getSurname());
        user.setEmail(userFormRegistration.getEmail());
        user.setPassword(passwordEncoder.encode(userFormRegistration.getPassword()));
        user.setStreet(userFormRegistration.getStreet());
        user.setCity(userFormRegistration.getCity());
        user.setZip(userFormRegistration.getZip());
        user.setPhone(userFormRegistration.getPhone());
        user.addRole(defaultRole);

        userRepository.save(user);

    }
}
