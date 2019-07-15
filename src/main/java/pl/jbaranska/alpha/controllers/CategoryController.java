package pl.jbaranska.alpha.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.jbaranska.alpha.models.CategoryForm;
import pl.jbaranska.alpha.services.CategoryServices;

import javax.jws.WebParam;

@Controller
@RequestMapping("/admin")
public class CategoryController {
    private CategoryServices categoryServices;

    public CategoryController(CategoryServices categoryServices) {
        this.categoryServices = categoryServices;
    }

    @GetMapping("/categories")
    public String showCategories(Model model)
    {
        model.addAttribute("categoryForm", new CategoryForm());
        model.addAttribute("categories", categoryServices.showCategoryList());
        return "category";
    }

    @PostMapping("/categories")
    public String addNewCategory(@ModelAttribute CategoryForm categoryForm,
            Model model){
        categoryServices.addCategory(categoryForm);
        model.addAttribute("categories", categoryServices.showCategoryList());
        return "category";
    }

}
