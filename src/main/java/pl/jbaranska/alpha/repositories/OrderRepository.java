package pl.jbaranska.alpha.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jbaranska.alpha.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
