package by.step.zimin.eshop.controller;

import by.step.zimin.eshop.service.BucketService;
import by.step.zimin.eshop.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final BucketService bucketService;
    private final OrderService orderService;

    @GetMapping("/create/new")
    public void getFormOrder(Model model, Principal principal){
        orderService.createOrder(principal.getName());

    }

}
