package pl.jbaranska.alpha.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jbaranska.alpha.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
