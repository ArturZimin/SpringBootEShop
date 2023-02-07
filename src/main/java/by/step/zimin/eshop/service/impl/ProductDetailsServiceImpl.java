package by.step.zimin.eshop.service.impl;

import by.step.zimin.eshop.model.ProductDetails;
import by.step.zimin.eshop.repository.ProductDetailsRepository;
import by.step.zimin.eshop.service.ProductDetailsService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductDetailsServiceImpl implements ProductDetailsService {

    private static final Logger log= LoggerFactory.getLogger(ProductDetailsServiceImpl.class);


    private final ProductDetailsRepository productDetailsRepository;



    @Override
    @Transactional
    public boolean addProductDetails(ProductDetails productDetails) {
        System.out.println("before save productDetails: "+productDetails);
        productDetailsRepository.save(productDetails);
        return true;
    }

    @Override
    @Transactional
    public void save(ProductDetails productDetails) {
        productDetailsRepository.save(productDetails);
    }

    @Override
    @Transactional
    public Optional<ProductDetails> findById(Long id) {
        return productDetailsRepository.findById(id);
    }
}
