package pl.jbaranska.alpha.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.jbaranska.alpha.entity.Category;
import pl.jbaranska.alpha.models.ProductForm;
import pl.jbaranska.alpha.services.CategoryServices;
import pl.jbaranska.alpha.services.ProductServices;

import java.sql.SQLException;

@Controller
@RequestMapping("/admin")
public class ProductController {
    private CategoryServices categoryServices;
    private ProductServices productServices;

    public ProductController(CategoryServices categoryServices, ProductServices productServices) {
        this.categoryServices = categoryServices;
        this.productServices = productServices;
    }

    @GetMapping("/products/{categoryId}")
    public String showProductsInCategory(@PathVariable String categoryId, Model model){
        model.addAttribute("category", categoryServices.showCategory(Integer.valueOf(categoryId)));
        model.addAttribute("productForm", new ProductForm());
        model.addAttribute("productsList", productServices.showProductsInCategory(Integer.valueOf(categoryId)));
        return "productsInCategory";
    }

    @PostMapping("/products/{categoryId}")
    public String addNewProducts(@PathVariable String categoryId, @ModelAttribute ProductForm productForm, Model model, BindingResult bindingResult){
        Integer intCategoryId = Integer.valueOf(productForm.getCategoryId());
        try {
            productServices.saveProduct(productForm, intCategoryId);
        }catch(SQLException e)
        {
            bindingResult.reject("save-in-db-error", "Błąd przy zapisie do bazy");
            return "productsInCategory";
        }
        model.addAttribute("category", categoryServices.showCategory(intCategoryId));
        model.addAttribute("productForm", new ProductForm());
        model.addAttribute("productsList", productServices.showProductsInCategory(intCategoryId));

        return "productsInCategory";
    }
}
