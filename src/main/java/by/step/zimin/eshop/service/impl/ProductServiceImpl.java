package by.step.zimin.eshop.service.impl;


import by.step.zimin.eshop.dto.ProductDto;
import by.step.zimin.eshop.model.Product;
import by.step.zimin.eshop.repository.ProductRepository;
import by.step.zimin.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {



    @Autowired
    private ProductRepository productRepository;





    @Override
    public List<ProductDto> getAll() {
       List<Product> productList= productRepository.findAll();
       return productToProductDto(productList);
    }

    public List<ProductDto> productToProductDto(List<Product> product){
        List<ProductDto> list=product.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return list;
    }

    public ProductDto toDto(Product product){
        ProductDto dto= ProductDto.builder()
                .title(product.getTitle())
                .price(product.getPrice())
                .build();
        return dto;
    }
}
