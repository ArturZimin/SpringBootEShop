package by.step.zimin.eshop.service;

import by.step.zimin.eshop.model.Category;

import java.util.Optional;

public interface CategoryService {
    void addCategory(Category category);
   Category getCategoryByCategoryName(String categoryName);
}
