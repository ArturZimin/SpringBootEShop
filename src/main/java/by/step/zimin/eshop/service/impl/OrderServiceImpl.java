package by.step.zimin.eshop.service.impl;

import by.step.zimin.eshop.model.*;
import by.step.zimin.eshop.repository.OrderRepository;
import by.step.zimin.eshop.service.*;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Log4j2
public class OrderServiceImpl implements OrderService {

    //private static final Logger log= LoggerFactory.getLogger(OrderServiceImpl.class);


    @Autowired
    private final UserService userService;
    @Autowired
    private final BucketService bucketService;
    @Autowired
    private final OrderRepository orderRepository;
    @Autowired
    private final OrderDetailsService orderDetailsService;


    @Override
    @Transactional
    public Order createOrder(String name) {
        User user = userService.findByName(name);
        List<Product> productList = user.getBucket().getProductList();

        Order order = Order.builder()
                .sum(new BigDecimal(bucketService.getBucketByUser(name).getSum()))
                .orderStatus(OrderStatus.ADD)
                .address(user.getAddress())
                .created(LocalDateTime.of(LocalDate.now(), LocalTime.now()))
                .updated(LocalDateTime.of(LocalDate.now(), LocalTime.now()))
                .user(user)
                .build();
        Order saved = orderRepository.save(order);
        return saved;
    }

    @Override
    @Transactional
    public Order findOrderByUsername(String userName) {
        return orderRepository.findOrderByUser_Username(userName);
    }


}
