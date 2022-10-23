package by.step.zimin.eshop.controller;

import by.step.zimin.eshop.dto.UserDto;
import by.step.zimin.eshop.model.Role;
import by.step.zimin.eshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users") //http://localhost:8080/users
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/new")//http://localhost:8080/users/new
    public String newUser(Model model) {
        model.addAttribute("user", new UserDto());
        return "addUser";
    }

    @PostMapping("/new")//http://localhost:8080/users/new
    public String saveUser(UserDto userDto, Model model) {
        if (userService.save(userDto)) {
            return "redirect:/";//return index.html  (на основную страницу)
        } else {
            model.addAttribute("user", userDto);
            return "addUser";//возвращаемся на страничку user.html
        }
    }

    @GetMapping("/get/all")
    public String getAllUsers(Model model) {

        model.addAttribute("users", userService.getAllUser());

        return "allUsers";
    }

    @PostMapping("/registration")
    public String registration(UserDto userDto, Model model) {
        userDto.setRole(Role.USER);
        if (userService.save(userDto)) {
            return "redirect:/";
        } else {
            model.addAttribute("user", userDto);
            return "registration";
        }
    }

}