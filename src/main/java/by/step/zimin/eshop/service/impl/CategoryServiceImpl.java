package by.step.zimin.eshop.service.impl;

import by.step.zimin.eshop.model.Category;
import by.step.zimin.eshop.repository.CategoryRepository;
import by.step.zimin.eshop.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

   private final CategoryRepository categoryRepository;
    @Override
    public void addCategory(Category category) {
        categoryRepository.save(category);
    }
}
