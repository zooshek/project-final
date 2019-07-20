package pl.jbaranska.alpha.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.jbaranska.alpha.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    List<Product> findProductsByCategoryId(Integer categoryId);

    List<Product> findDistinctByCategoryId(Integer categoryId);

   // @Query(value = "select c.SURNAME, c.NAME from PRODUCTS p", nativeQuery = true)
   // List<>
}

