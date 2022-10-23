package by.step.zimin.eshop.service;

import by.step.zimin.eshop.dto.ProductDto;
import by.step.zimin.eshop.model.Product;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAll();
}
