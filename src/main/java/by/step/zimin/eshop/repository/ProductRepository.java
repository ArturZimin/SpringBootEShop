package by.step.zimin.eshop.repository;

import by.step.zimin.eshop.dto.ProductDto;
import by.step.zimin.eshop.model.Category;
import by.step.zimin.eshop.model.Product;
import by.step.zimin.eshop.model.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {


    List<Product> findAll();
    List<Product> findAllByCategory_PodCategory(String podCategory);



    Optional<Product> findAllByCategory_PodCategoryIgnoreCaseAndTitle(String category, String title);
    Optional<Product> findAllByTitle( String title);

    Optional<Product> findAllByCategory_PodCategoryIgnoreCase(String category);

    List<Product> findAllByOrderByPrice();

    List<Product> findAllByOrderByProductDetails_YearProductionDesc();
}
