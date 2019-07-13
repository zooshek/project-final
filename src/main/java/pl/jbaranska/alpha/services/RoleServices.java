package pl.jbaranska.alpha.services;

import org.springframework.stereotype.Service;
import pl.jbaranska.alpha.entity.Role;
import pl.jbaranska.alpha.repositories.RoleRepository;

import java.util.List;
@Service
public class RoleServices {
    RoleRepository roleRepository;

    public RoleServices(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getRoles()
    {
        return roleRepository.findAll();
    }
}
