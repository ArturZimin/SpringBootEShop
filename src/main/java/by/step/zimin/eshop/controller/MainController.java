package by.step.zimin.eshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class MainController {

    @RequestMapping({"", "/"})  //можно 2 способами
    public String index() {
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