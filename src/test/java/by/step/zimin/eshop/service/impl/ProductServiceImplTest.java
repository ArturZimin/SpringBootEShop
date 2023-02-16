package by.step.zimin.eshop.service.impl;

import by.step.zimin.eshop.dto.BucketDto;
import by.step.zimin.eshop.dto.ProductDto;
import by.step.zimin.eshop.model.*;
import by.step.zimin.eshop.repository.BucketRepository;
import by.step.zimin.eshop.repository.DiscountRepository;
import by.step.zimin.eshop.repository.ProductRepository;
import by.step.zimin.eshop.repository.UserRepository;
import by.step.zimin.eshop.service.*;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
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
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProcessorService processorService;



    @MockBean
    private DiscountRepository discountRepository;
    @MockBean
    private ProductRepository productRepository;
    @MockBean
    private BucketRepository bucketRepository;


    private Product product;
    private Product product2;
    private User user;
    private Discount discount;
    private ProductDetails productDetails;
    private Category category;
    private Processor processor;

    private Bucket bucket;
    @BeforeEach
    public void initialize() {

        this.category = new Category();
        this.category.setCategoryTitle("Electronics");
        this.category.setPodCategory("Phone");

        this.processor = new Processor();
        this.processor.setName("Intel core 2 Duo");

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
        this.user.setUsername("Artur");
        this.user.setPassword("4444");
        this.user.setEmail("hgghgh@jhj.com");
        this.user.setAddress("Minsk Angarskaya 12");
        this.user.setRole(Role.USER);
        this.user.setBucket(bucket);

        List<Product> productList=new ArrayList<>();
        productList.add(product);
        bucket=new Bucket();
        bucket.setProductList(productList);
        bucket.setUser(user);
    }

    @Test
    void addProductToUserBucket() throws IOException,RuntimeException {

        MockMultipartFile file = new MockMultipartFile("file", "hjh".getBytes());
        discountService.save(this.discount);
        productService.addProduct(file, file, file, productService.toDto(product));
        userService.save(user);
        Mockito.when(userService.findByName("Artur")).thenReturn(this.user);
        productService.addProductToUserBucket(1L, "Artur");
        BucketDto bucketDto = bucketService.getBucketByUser(user.getUsername());
        Assertions.assertNotNull(bucketDto);
    }


    @Test
    @Disabled
    void getAll() throws IOException {
        MockMultipartFile file = new MockMultipartFile("file", "hjh".getBytes());
        productService.addProduct(file, file, file, productService.toDto(product));
        productService.addProduct(file, file, file, productService.toDto(product2));
        productService.save(product2);
        productService.save(product);
        List<ProductDto> list=productService.getAll();
        Assertions.assertNotNull(list);
//        Assertions.assertEquals(2,list.size());

    }


    @Test
    @Disabled
    void addProduct() throws IOException {
        MockMultipartFile file = new MockMultipartFile("file", "hjh".getBytes());
        productService.addProduct(file, file, file, productService.toDto(product));
        Boolean isAdded = productService.addProduct(file, file, file, productService.toDto(product2));
        Assertions.assertTrue(isAdded);

    }


    @Test
    void getPhones() {
        List<Product> listBefore = new ArrayList<>();
        listBefore.add(product2);
        listBefore.add(product);
        productService.save(product);
        productService.save(product2);
        List<ProductDto> productAfter = productService.getPhones();
//        Assert.assertEquals(listBefore.size(), productAfter.size());
    }

    @Test
    void deleteProduct() {
        productService.save(product);
        Mockito.when(productRepository.findById(product.getId())).thenReturn(java.util.Optional.ofNullable(product));
       Integer number= productService.deleteProduct(product.getId());
       Assert.assertEquals(java.util.Optional.of(200).get(),number);
    }








}