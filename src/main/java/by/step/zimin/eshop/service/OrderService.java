package by.step.zimin.eshop.service;

import by.step.zimin.eshop.model.Order;
import org.springframework.stereotype.Service;


public interface OrderService {
    Order createOrder(String name);
    Order findOrderByUsername(String userName);
}
