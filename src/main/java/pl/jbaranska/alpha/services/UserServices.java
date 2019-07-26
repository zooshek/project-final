package pl.jbaranska.alpha.services;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jbaranska.alpha.entity.Role;
import pl.jbaranska.alpha.entity.User;
import pl.jbaranska.alpha.exeptions.EmailAlreadyExistsException;
import pl.jbaranska.alpha.models.UserFormRegistration;
import pl.jbaranska.alpha.repositories.RoleRepository;
import pl.jbaranska.alpha.repositories.UserRepository;

import java.sql.SQLException;
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

    public boolean isUser()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().stream()
        .anyMatch(o -> ((GrantedAuthority) o).getAuthority().equalsIgnoreCase("ROLE_USER"))){
            return true;
        }
        return false;
    }

    public String getLoggedUserEmail()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken){
            return null;
        }
        return authentication.getName();
    }
    public Optional<User> getUser()
    {
        return userRepository.findFirstByEmail(getLoggedUserEmail());
    }

    @Transactional
    public void editUser(User user)throws SQLException {
        userRepository.save(user);
    }

    public Optional<User> getUserById(Integer id)
    {
        return userRepository.findById(id);
    }

    public boolean isAdmin()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().stream()
                .anyMatch(o -> ((GrantedAuthority) o).getAuthority().equalsIgnoreCase("ROLE_ADMIN"))){
            return true;
        }
        return false;
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
