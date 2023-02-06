package by.step.zimin.eshop.service.impl;

import by.step.zimin.eshop.model.OrderDetails;
import by.step.zimin.eshop.repository.OrderDetailsRepository;
import by.step.zimin.eshop.service.OrderDetailsService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class OrderDetailsServiceImpl implements OrderDetailsService {

    private static final Logger log= LoggerFactory.getLogger(OrderDetailsServiceImpl.class);


    @Autowired
    private final OrderDetailsRepository orderDetailsRepository;

    @Transactional
    public OrderDetails save(OrderDetails orderDetails) {
        return orderDetailsRepository.save(orderDetails);
    }
}
