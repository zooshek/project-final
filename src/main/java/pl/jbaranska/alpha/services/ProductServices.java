package pl.jbaranska.alpha.services;

import org.springframework.stereotype.Service;
import pl.jbaranska.alpha.entity.Category;
import pl.jbaranska.alpha.entity.Product;
import pl.jbaranska.alpha.models.ProductForm;
import pl.jbaranska.alpha.repositories.CategoryRepository;
import pl.jbaranska.alpha.repositories.ProductRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServices {

    public static final String CATEGORY_PIZZA = "Pizza";
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public ProductServices(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public void saveProduct (ProductForm productForm, Integer categoryId) throws SQLException {
        {
            Product product = new Product();
            if(categoryRepository.findById(categoryId).isPresent())
            {
                product.setProduct(productForm.getProduct());
                product.setSize(productForm.getSize());
                product.setPrice(productForm.getPrice());
                product.setCategoryId(categoryId);
                System.out.println(product);
                productRepository.save(product);
            }else {
                System.out.println("bledna kategoria");
            }
        }
    }
    public List<Product> showProductsInCategory(Integer categoryId){
        return productRepository.findProductsByCategoryId(categoryId);
    }
    public List<Product> getProductsByCategoryName(String categoryName)
    {
        Integer categoryId = categoryRepository.findByCategory(categoryName).get().getId();
        return productRepository.findProductsByCategoryId(categoryId);
    }
}
