package by.step.zimin.eshop.controller;

import by.step.zimin.eshop.service.BucketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class MainController {

    private final BucketService bucketService;

    public MainController(BucketService bucketService) {
        this.bucketService = bucketService;
    }

    @RequestMapping({"", "/"})  //можно 2 способами
    public String index(Model model,Principal principal) {
        if (principal!=null){
          Long amount=  bucketService.getAmountInBucket(principal.getName());
          model.addAttribute("amount",amount);
        }
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