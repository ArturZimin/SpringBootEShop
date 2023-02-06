package by.step.zimin.eshop.service.impl;

import by.step.zimin.eshop.dto.ProductDto;
import by.step.zimin.eshop.model.*;
import by.step.zimin.eshop.repository.ProductRepository;
import by.step.zimin.eshop.service.BucketService;
import by.step.zimin.eshop.service.ProductService;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class BucketServiceImplTest {


    @Autowired
    private BucketService bucketService;

    @Autowired
    private ProductService productService;

    private User user;
    private ProductDto productDto;

    @Before
    public void initializing() {
        user = new User();
        user.setEnable(false);
        user.setUsername("Artur V");
        user.setPassword("4444");
        user.setEmail("hgghgh@jhj.com");
        user.setAddress("Minsk Angarskaya 12");
        user.setRole(Role.USER);

        productDto.builder()
                .id(1l)
                .title("Electronics")
                .price(new BigDecimal("120.10"))
                .imageProduct("product.getImageProduct()")
                .imageProduct2("product.getImageProduct2()")
                .imageProduct3("product.getImageProduct3()")
                .amount(4l)
                .currencyType(CurrencyType.BELRUBLE)
                .categoryTitle("hjhj")
                .podCategory("jkj")
                .name("Intel")
                .countCore(4)
                .frequency(70)
                .accumulatorCapacity(2022)
                .versionOS(1.2)
                .operationSystem(OperationSystem.ANDROID)
                .rearCamera(null)
                .frontCamera(null)
                .ramMemory(null)
                .inMemory(null)
                .yearProduction(null)
                .displaySize(null)
                .countSim(1)
                .color(Color.BLUE)
                .build();

    }

    @After
    @Test
    void createBucket() throws IOException {
        MockMultipartFile file = new MockMultipartFile("file", "hjh".getBytes());
        List<Long> longList = List.of(1L, 2L);
        Boolean isAdded = productService.addProduct(file, file, file, productDto);

        Bucket bucketCreated = bucketService.createBucket(user, longList);
        System.out.println(isAdded + " " + bucketCreated);
    }

    @Test
    void getProductById() {
    }

    @Test
    void addProduct() {
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