package by.step.zimin.eshop.service.impl;

import by.step.zimin.eshop.model.Color;
import by.step.zimin.eshop.model.ProductDetails;
import by.step.zimin.eshop.service.ProductDetailsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class ProductDetailsServiceImplTest {
    @Autowired
    private ProductDetailsService productDetailsService;

    public ProductDetails productDetails;

    @BeforeEach
    public void initialization(){
        productDetails=new ProductDetails();
        productDetails.setId(1L);
        productDetails.setAccumulatorCapacity(5500);
        productDetails.setColor(Color.GOLD);
    }
    @Test
    void addProductDetails() {
        productDetailsService.save(this.productDetails);
        Optional<ProductDetails> productDetails1=productDetailsService.findById(productDetails.getId());
        Assertions.assertNotNull(productDetails1);
        Assertions.assertEquals(productDetails1.get().getAccumulatorCapacity(),productDetails.getAccumulatorCapacity());
    }
}