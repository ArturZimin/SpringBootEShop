package by.step.zimin.eshop.service.impl;


import by.step.zimin.eshop.dto.ProductDto;
import by.step.zimin.eshop.model.Bucket;
import by.step.zimin.eshop.model.Product;
import by.step.zimin.eshop.model.User;
import by.step.zimin.eshop.repository.ProductRepository;
import by.step.zimin.eshop.service.BucketService;
import by.step.zimin.eshop.service.ProductService;
import by.step.zimin.eshop.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserService userService;
    private final BucketService bucketService;


    public ProductServiceImpl(ProductRepository productRepository, UserService userService, BucketService bucketService) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.bucketService = bucketService;
    }


    @Override
    public List<ProductDto> getAll() {
        List<Product> productList = productRepository.findAll();

        return productListToProductListDto(productList);
    }

    @Override
    @Transactional
    public void addProductToUserBucket(Long productId, String username) {
        User user = userService.findByName(username);
        if (user == null) {
            throw new RuntimeException("User not found : " + username);
        }

        Bucket bucket = user.getBucket();
        if (bucket == null) {
            Bucket newBucket = bucketService.createBucket(user, Collections.singletonList(productId));
            user.setBucket(newBucket);
            userService.save(user);
        } else {
            bucketService.addProduct(bucket, Collections.singletonList(productId));
        }

    }

    @Override
    @Transactional
    public Boolean addProduct(ProductDto productDto) {
        Product product = toProduct(productDto);
        Product productSaved = productRepository.save(product);
        if (productSaved.getId() != null) {
            return true;
        } else {
            return false;
        }

    }

    public List<ProductDto> productListToProductListDto(List<Product> product) {
        List<ProductDto> list = product.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return list;
    }

    public Product toProduct(ProductDto productDto) {
        Product product = Product.builder()
                .id(productDto.getId())
                .title(productDto.getTitle())
                .price(productDto.getPrice())
                .categories(productDto.getCategories())
                .imageProduct(productDto.getImageProduct())
                .currencyType(productDto.getCurrencyType())
                .productDetails(productDto.getProductDetails())
                .processor(productDto.getProcessor())
                .build();
        return product;
    }

    public ProductDto toDto(Product product) {
        ProductDto dto = ProductDto.builder()
                .id(product.getId())
                .title(product.getTitle())
                .price(product.getPrice())
                .imageProduct(product.getImageProduct())
                .categories(product.getCategories())
                .currencyType(product.getCurrencyType())
                .productDetails(product.getProductDetails())
                .processor(product.getProcessor())
                .build();
        return dto;
    }
}
