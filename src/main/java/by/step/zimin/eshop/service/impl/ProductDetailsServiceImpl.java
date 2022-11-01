package by.step.zimin.eshop.service.impl;

import by.step.zimin.eshop.model.ProductDetails;
import by.step.zimin.eshop.repository.ProductDetailsRepository;
import by.step.zimin.eshop.service.ProductDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductDetailsServiceImpl implements ProductDetailsService {

    private final ProductDetailsRepository productDetailsRepository;



    @Override
    public boolean addProductDetails(ProductDetails productDetails) {
        System.out.println("before save productDetails: "+productDetails);
        productDetailsRepository.save(productDetails);
        return true;
    }
}
