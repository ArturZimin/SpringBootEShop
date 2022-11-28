package by.step.zimin.eshop.controller;

import by.step.zimin.eshop.dto.ProductDto;
import by.step.zimin.eshop.service.BucketService;
import by.step.zimin.eshop.service.HeaderImagesService;
import by.step.zimin.eshop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
public class MainController {


    private final BucketService bucketService;
    private final ProductService productService;
    private final HeaderImagesService headerImagesService;


    @RequestMapping({"", "/"})  //можно 2 способами
    public String index(@RequestParam(required = false) String sortBy, Model model, Principal principal) {
        List<ProductDto> list = null;
        if (sortBy==null) {
            list = productService.getAll();
        } else if(sortBy.equals("price")) {
            list =productService.getAllProductsSortByPrice();
        }else if(sortBy.equals("year")){
            list =productService.getAllProductsSortByYear();
        }
        if (list == null) {
            throw new RuntimeException("The list of products is null!");
        }
        if (principal != null) {
            Long amount = bucketService.getAmountInBucket(principal.getName());
            model.addAttribute("amount", amount);
        }
        String icon = headerImagesService.getIconCompany();
        model.addAttribute("iconCompany", icon);
        model.addAttribute("products", list);
        System.out.println(sortBy);
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