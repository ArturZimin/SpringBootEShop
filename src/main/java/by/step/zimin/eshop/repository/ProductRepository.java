package by.step.zimin.eshop.repository;

import by.step.zimin.eshop.dto.ProductDto;
import by.step.zimin.eshop.model.Category;
import by.step.zimin.eshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {


    List<Product> findAll();
    List<Product> findAllByCategory_PodCategory(String podCategory);
}
