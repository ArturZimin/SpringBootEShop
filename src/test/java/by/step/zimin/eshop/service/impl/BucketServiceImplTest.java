package by.step.zimin.eshop.service.impl;

import by.step.zimin.eshop.dto.BucketDto;
import by.step.zimin.eshop.dto.ProductDto;
import by.step.zimin.eshop.model.*;
import by.step.zimin.eshop.repository.ProductRepository;
import by.step.zimin.eshop.service.BucketService;
import by.step.zimin.eshop.service.CategoryService;
import by.step.zimin.eshop.service.ProductService;
import by.step.zimin.eshop.service.UserService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class BucketServiceImplTest {


    @Autowired
    private BucketService bucketService;

    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @MockBean
    private Processor processor;
    @MockBean
    private Category category;
    @MockBean
    private Discount discount;


    private User user;
    private ProductDto productDto;
    private Bucket bucket;
    private Product product;


    @BeforeEach
    public void initializing() {
        user = new User();
        user.setEnable(false);
        user.setUsername("Artur V");
        user.setPassword("4444");
        user.setEmail("hgghgh@jhj.com");
        user.setAddress("Minsk Angarskaya 12");
        user.setRole(Role.USER);
//categoryService.addCategory(category);

        this.product = new Product();
        this.product.setId(1L);
        this.product.setCategory(category);
        this.product.setProcessor(processor);
        this.product.setTitle("Nokia 3310");
        this.product.setDiscount(discount);


        productDto = ProductDto.builder()
                .id(1l)
                .title("Electronics")
                .price(new BigDecimal("120.10"))
                .imageProduct("product.getImageProduct()")
                .imageProduct2("product.getImageProduct2()")
                .imageProduct3("product.getImageProduct3()")
                .amount(4l)
                .currencyType(CurrencyType.BELRUBLE)
                .categoryTitle("hjhj")
                .color(Color.GOLD)
                .podCategory("jkj")
                .name("Intel")
                .countCore(4)
                .frequency(70)
                .accumulatorCapacity(2022)
                .versionOS(1.2)
                .operationSystem(OperationSystem.ANDROID)
                .rearCamera(5.5)
                .frontCamera(2.0)
                .ramMemory(4)
                .inMemory(500)
                .displaySize(5.7)
                .countSim(1)
                .color(Color.BLUE)
                .build();
        List<Product> productList = new ArrayList<>();
        productList.add(this.product);

        this.bucket = new Bucket();
        bucket.setUser(user);
        bucket.setId(1L);
        bucket.setProductList(productList);
    }


    @Test
    void createBucket() throws IOException {
        MockMultipartFile file = new MockMultipartFile("file", "hjh".getBytes());
        List<Long> longList = List.of(1L);
        Bucket createdBucket = bucketService.createBucket(user, longList);
        Assert.assertNotNull(createdBucket);

    }

    @Test
    void getProductById() {
        List<Long> longList = List.of(1L);
        List<Product> list = bucketService.getProductById(longList);
        Assert.assertEquals(1, list.size());
    }

    @Test
    void addProduct() {
        List<Long> longList = List.of(1L);
        List<Product> list = bucketService.getProductById(longList);
        userService.save(user);
        productService.save(product);
        bucketService.addProduct(bucket, longList);
//        BucketDto bucketDto2 = bucketService.getBucketByUser(this.user.getUsername());
//        Assert.assertNotNull(bucketDto2);
    }

    @Test
    void getBucketByUser() {
    }

    @Test
    void deleteProduct() {
    }

    @Test
    void getAmountInBucket() {
    }
}