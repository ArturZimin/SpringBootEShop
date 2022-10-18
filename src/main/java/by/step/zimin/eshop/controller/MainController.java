package by.step.zimin.eshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping({"","/"})  //можно 2 способами
    public String index (){
        return"index";
    }

    @RequestMapping("/login") //если запрос по ссылку на логин
    public String login (){

        return"login";//то переходит на login.html
    }


    @RequestMapping("/logout")
    public String logout(){
        return "logout";
    }





}