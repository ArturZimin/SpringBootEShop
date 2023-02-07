package by.step.zimin.eshop.service;

import by.step.zimin.eshop.model.ProductDetails;

import java.util.Optional;

public interface ProductDetailsService {
    boolean addProductDetails(ProductDetails productDetails);
    void save(ProductDetails productDetails);
    Optional<ProductDetails> findById(Long id);
}
