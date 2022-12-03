package by.step.zimin.eshop.controller;

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

    @GetMapping("/get/form")
    public String getFormOrder(Model model, Principal principal){
        //model.addAttribute("userName",principal.getName());
        return "formOrder";
    }

}
