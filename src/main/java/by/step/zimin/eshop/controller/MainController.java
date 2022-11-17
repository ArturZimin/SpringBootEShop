package by.step.zimin.eshop.controller;

import by.step.zimin.eshop.dto.ProductDto;
import by.step.zimin.eshop.service.BucketService;
import by.step.zimin.eshop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
public class MainController {


    private final BucketService bucketService;
    private final ProductService productService;


    @RequestMapping({"", "/"})  //можно 2 способами
    public String index(Model model, Principal principal) {
        List<ProductDto> list = productService.getAll();
        int c=0;
       for(ProductDto p:list){
           if (p.getImageProduct2().isEmpty()){
               c++;
           }

       }
        System.out.println(" image product count: "+c);
        if (list == null) {
            throw new RuntimeException("The list of products is null!");
        }
        if (principal != null) {
            Long amount = bucketService.getAmountInBucket(principal.getName());
            model.addAttribute("amount", amount);
        }

        model.addAttribute("products", list);

        return "index";
    }

    @RequestMapping("/login") //если запрос по ссылке на логин
    public String login() {
        //то переходит на login.html
        return "login";
    }

    @RequestMapping("/users/registration")
    public String registration() {
        return "registration";
    }


    @RequestMapping("/logout")
    public String logout() {
        return "logout";
    }

    @RequestMapping("/loginError")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";//то переходит на login.html
    }


}