package pl.jbaranska.alpha.services;

import org.springframework.stereotype.Service;
import pl.jbaranska.alpha.entity.Category;
import pl.jbaranska.alpha.entity.Product;
import pl.jbaranska.alpha.repositories.CategoryRepository;
import pl.jbaranska.alpha.repositories.ProductRepository;

import java.util.List;

@Service
public class OrderServices {
    public static final String CATEGORY_PIZZA = "Pizza";
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public OrderServices(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Product> getPizzaList(){

        Category categoryPizza = categoryRepository.findByCategory(CATEGORY_PIZZA).get();
             return   productRepository.findDistinctByCategoryId(categoryPizza.getId());

    }
}
