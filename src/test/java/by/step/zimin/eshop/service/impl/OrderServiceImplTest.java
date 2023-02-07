package by.step.zimin.eshop.service.impl;

import by.step.zimin.eshop.controller.OrderController;
import by.step.zimin.eshop.model.*;
import by.step.zimin.eshop.repository.OrderRepository;
import by.step.zimin.eshop.service.OrderService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class OrderServiceImplTest {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;

    @MockBean
    private OrderController orderController;
    @MockBean
    private OrderServiceImpl orderServiceImpl;




    private Order order;
    private User user;
    private Bucket bucket;
    private Product product;

    @BeforeEach
    public void initialization(){

        this.product = new Product();
        this.product.setId(1L);
        this.product.setTitle("Nokia 3310");


        List<Product> productList=new ArrayList<>();
        productList.add(product);
        bucket=new Bucket();
        bucket.setProductList(productList);
        bucket.setUser(user);

        order=new Order();
        order.setId(1l);
        order.setSum(new BigDecimal("500"));
        order.setAddress("Minsk Kalinowskogo 111");
        order.setOrderStatus(OrderStatus.ADD);
        order.setCreated(LocalDateTime.now());

        this.user = new User();
        this.user.setEnable(false);
        this.user.setUsername("Artur");
        this.user.setPassword("4444");
        this.user.setEmail("hgghgh@jhj.com");
        this.user.setAddress("Minsk Angarskaya 12");
        this.user.setRole(Role.USER);
        this.user.setBucket(bucket);
    }

    @Test
    void createOrder() {

        Order found= orderService.createOrder(user.getUsername());
        System.out.println(found);
//        Assert.assertNotNull(found);
    }
}