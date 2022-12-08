package by.step.zimin.eshop.service.impl;

import by.step.zimin.eshop.model.*;
import by.step.zimin.eshop.repository.OrderRepository;
import by.step.zimin.eshop.service.*;
import lombok.AllArgsConstructor;
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
public class OrderServiceImpl implements OrderService {
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
    public void createOrder(String name) {
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
        orderRepository.save(order);

    }


}
