package pl.jbaranska.alpha.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.jbaranska.alpha.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    List<Product> findProductsByCategoryId(Integer categoryId);

    @Query(value = "SELECT distinct PRODUCT_NAME FROM PRODUCTS WHERE CATEGORY_ID = :categoryId", nativeQuery = true)
    List<String> selectDistinctName (@Param("categoryId") Integer categoryId);


    //@Query(value = "SELECT distinct PRODUCT_NAME FROM PRODUCTS WHERE CATEGORY_ID = :categoryId", nativeQuery = true)
    //List<String> selectDistinctName (@Param("categoryId") Integer categoryId);
}


