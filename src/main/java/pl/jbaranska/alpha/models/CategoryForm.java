package pl.jbaranska.alpha.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryForm {
    private String categoryName;

    public CategoryForm() {
    }

    public CategoryForm(String categoryName) {
        this.categoryName = categoryName;
    }
}
