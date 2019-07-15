package pl.jbaranska.alpha.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jbaranska.alpha.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
}
