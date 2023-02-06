package by.step.zimin.eshop.service.impl;

import by.step.zimin.eshop.model.Category;
import by.step.zimin.eshop.service.CategoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class CategoryServiceImplTest {

    @Autowired
    private CategoryService categoryService;

    private Category category;

    @BeforeEach
    public void initializing(){
        category=new Category();
        category.setCategoryTitle("Transport");
        category.setPodCategory("Car");
        category.setId(1L);
    }

    @Test
    void addCategory() {
        categoryService.addCategory(category);
        Category categorySearch=categoryService.getCategoryByCategoryName(category.getCategoryTitle());
        Assertions.assertNotNull(categorySearch);
        Assertions.assertEquals(category.getPodCategory(),categorySearch.getPodCategory());
    }
}