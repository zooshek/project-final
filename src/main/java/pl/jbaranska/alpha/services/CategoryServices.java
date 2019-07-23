package pl.jbaranska.alpha.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jbaranska.alpha.entity.Category;
import pl.jbaranska.alpha.models.CategoryForm;
import pl.jbaranska.alpha.repositories.CategoryRepository;

import java.util.List;
import java.util.Set;

@Service
public class CategoryServices {
    private CategoryRepository categoryRepository;

    public CategoryServices(CategoryRepository categoryRepository) {

        this.categoryRepository = categoryRepository;
    }

    public List<Category> showCategoryList()
    {
        return categoryRepository.findAll();
    }

@Transactional
    public void addCategory(CategoryForm categoryForm)
    {
         categoryRepository.findByCategory(categoryForm.getCategoryName())
         .orElseGet(() -> categoryRepository.save(new Category(categoryForm.getCategoryName())));
    }
    public Category  showCategory(Integer id)
    {
        return categoryRepository.getOne(id);
    }
}
