package by.step.zimin.eshop.service.impl;

import by.step.zimin.eshop.model.Category;
import by.step.zimin.eshop.repository.CategoryRepository;
import by.step.zimin.eshop.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Log4j2
public class CategoryServiceImpl implements CategoryService {

//    private static final Logger log= LoggerFactory.getLogger(CategoryServiceImpl.class);


    private final CategoryRepository categoryRepository;

    @Override
    public void addCategory(Category category) {
        categoryRepository.save(category);
        log.info("The category was added.");
    }

    @Override
    public Category getCategoryByCategoryName(String categoryTitle) {
       Optional<Category> category= categoryRepository.findByCategoryTitle(categoryTitle);
       if (category.get()!=null){
           log.info("The category was found! "+category.get());
       }
        return category.get();
    }
}
