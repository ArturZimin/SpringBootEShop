package by.step.zimin.eshop.repository;

import by.step.zimin.eshop.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findByCategoryTitle(String categoryTitle);

}
