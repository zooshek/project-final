package pl.jbaranska.alpha.repositories;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jbaranska.alpha.entity.Order;
import pl.jbaranska.alpha.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllByUser(User user);

    Optional<Order> findOrderById(Integer orderId);

    List<Order> findByUser(User user);
}
