package by.step.zimin.eshop.service.impl;

import by.step.zimin.eshop.dto.BucketDto;
import by.step.zimin.eshop.dto.ProductDto;
import by.step.zimin.eshop.model.*;
import by.step.zimin.eshop.service.BucketService;
import by.step.zimin.eshop.service.DiscountService;
import by.step.zimin.eshop.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class ProductServiceImplTest {

    @Autowired
    private UserService userService;
    @Autowired
    private ProductServiceImpl productService;
    @Autowired
    private BucketService bucketService;
    @Autowired
    private DiscountService discountService;
//    @Autowired
//    private ProductDetailsService productDetailsService;

    private Product product;
    private Product product2;
    private User user;
    private Discount discount;
    private ProductDetails productDetails;

    @BeforeEach
    public void initialize() {

        Category category = new Category();
        category.setCategoryTitle("Electronics");
        category.setPodCategory("Phone");

        Processor processor = new Processor();
        processor.setName("Intel core 2 Duo");

        this.discount = new Discount();
        this.discount.setId(1L);
        this.discount.setDiscount(6);
        this.discountService.save(discount);

        this.product = new Product();
        this.product.setId(1L);
        this.product.setCategory(category);
        this.product.setProcessor(processor);
        this.product.setTitle("Nokia 3310");
        this.product.setDiscount(discount);

        this.productDetails = new ProductDetails();
        this.productDetails.setColor(Color.BLUE);
        this.productDetails.setId(1l);
        this.productDetails.setAccumulatorCapacity(2200);


        this.product2 = new Product();
        this.product2.setId(2L);
        this.product2.setCategory(category);

        this.product2.setProcessor(processor);
        this.product2.setTitle("HP Pavilion 5510");
        this.product2.setProductDetails(productDetails);

        this.user = new User();
        this.user.setEnable(false);
        this.user.setUsername("Artur V");
        this.user.setPassword("4444");
        this.user.setEmail("hgghgh@jhj.com");
        this.user.setAddress("Minsk Angarskaya 12");
        this.user.setRole(Role.USER);

    }

    @Test
    @Transactional
    void getAll() throws IOException {
        MockMultipartFile file = new MockMultipartFile("file", "hjh".getBytes());
        productService.addProduct(file, file, file, productService.toDto(product));
        productService.addProduct(file, file, file, productService.toDto(product2));
        ProductDto productDto = productService.getProductById(product.getId());
        Assertions.assertNotNull(productDto);
        Assertions.assertEquals(productDto.getTitle(), product.getTitle());
    }

    @Test
    void addProductToUserBucket() throws IOException {
        MockMultipartFile file = new MockMultipartFile("file", "hjh".getBytes());

        productService.addProduct(file, file, file, productService.toDto(product));
        userService.save(user);

        productService.addProductToUserBucket(1L, "Artur V");
        BucketDto bucketDto = bucketService.getBucketByUser(user.getUsername());
        Assertions.assertNotNull(bucketDto);
    }

    @Test
    void addProduct() throws IOException {
        MockMultipartFile file = new MockMultipartFile("file", "hjh".getBytes());
        productService.addProduct(file, file, file, productService.toDto(product));
        Boolean isAdded = productService.addProduct(file, file, file, productService.toDto(product2));
        Assertions.assertTrue(isAdded);

    }


    @Test
    void getPhones() {
        String cat = "Phone";
        List<Product> productList = new ArrayList<>(2);
        productList.add(product);
        productList.add(product2);
//        Mockito.when(productRepository.findAllByCategory_PodCategory(cat)).thenReturn(productList);
//        productService.addProduct(product);
//        productRepository.save(product2);
//        List<ProductDto> productList2 = productService.getPhones();
//        Assertions.assertEquals(2, productList2.size());
    }

    @Test
    void deleteProduct() {
    }

    @Test
    void findByOrderByTitle() {
    }

    @Test
    void minusOneForAmount() {
    }

    @Test
    void getAmount() {
    }


}