package by.step.zimin.eshop.service;

import by.step.zimin.eshop.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAll();

    void addProductToUserBucket(Long productId, String username);

    Boolean addProduct(ProductDto productDto);
}
