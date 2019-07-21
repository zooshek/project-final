package pl.jbaranska.alpha.services;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jbaranska.alpha.entity.Role;
import pl.jbaranska.alpha.entity.User;
import pl.jbaranska.alpha.repositories.RoleRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class RoleServices {
    public static final String ROLE_USER = "ROLE_USER";
    private RoleRepository roleRepository;

    public RoleServices(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role addDefaultUserRole() {
        if (roleRepository.findByRole(ROLE_USER).isPresent()) {
            return roleRepository.findByRole(ROLE_USER).get();
        } else {
            return roleRepository.save(new Role(ROLE_USER));
        }
    }

}
