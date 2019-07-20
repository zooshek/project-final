package pl.jbaranska.alpha.services;

import org.springframework.stereotype.Service;
import pl.jbaranska.alpha.entity.Category;
import pl.jbaranska.alpha.entity.Product;
import pl.jbaranska.alpha.models.ProductForm;
import pl.jbaranska.alpha.repositories.CategoryRepository;
import pl.jbaranska.alpha.repositories.ProductRepository;

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

    public void saveProduct (ProductForm productForm, Integer categoryId){
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

    public List<String> getPizzaList(){

        Category categoryPizza = categoryRepository.findByCategory(CATEGORY_PIZZA).get();
        return   productRepository.selectDistinctName(categoryPizza.getId());

    }
    public List<Product> showProductsInCategory(String categoryName){
        Category category = categoryRepository.findByCategory(categoryName).get();
        return productRepository.findProductsByCategoryId(category.getId());
    }
    public List<Product> showAllProducts(){
        return productRepository.findAll();
    }

    public List<Product> showProductsInCategory(Integer categoryId){
        return productRepository.findProductsByCategoryId(categoryId);
    }
    public List<Product> showProductPizza(){
        Category categoryPizza = categoryRepository.findByCategory(CATEGORY_PIZZA).get();
        return productRepository.findProductsByCategoryId(categoryPizza.getId());
    }
    public List<Product> getProductsByName(String productName)
    {
        return productRepository.findProductsByProduct(productName);
    }
}