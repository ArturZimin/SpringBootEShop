package by.step.zimin.eshop.controller;

import by.step.zimin.eshop.dto.UserDto;
import by.step.zimin.eshop.model.Role;
import by.step.zimin.eshop.model.User;
import by.step.zimin.eshop.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.Objects;

@Controller
@RequestMapping("/users") //http://localhost:8080/users
@AllArgsConstructor
public class UserController {


    private final UserService userService;


    @GetMapping(value = "/get/registration/form")
    public String getRegistrationForm() {
        return "registration";
    }


    @GetMapping("/new")//http://localhost:8080/users/new
    public String newUser(Model model) {
        model.addAttribute("user", new UserDto());
        return "addUser";
    }

    @PostMapping("/new")//http://localhost:8080/users/new
    public String addNewUser(UserDto userDto, Model model) {
        if (userService.save(userDto)) {
            return "redirect:/";//return index.html  (на основную страницу)
        } else {
            String response = "The entered data does not match the requirements! ";
            throw new RuntimeException(response);
        }
    }

    @GetMapping("/get/all")
    public String getAllUsers(Model model) {

        model.addAttribute("users", userService.getAllUser());

        return "allUsers";
    }

    @PostMapping("/registration")
    public String registration(UserDto userDto, Model model) {
        System.out.println(userDto);
        userDto.setRole(Role.USER);
        User user = userService.registration(userDto);
        if (user != null) {
            model.addAttribute("message", "Success! A verification email has been sent to your email address.");
            return "/login";
        } else {
            throw new RuntimeException("The entered data does not match the requirements!");
        }
    }

    @GetMapping("/update")
    public String getUserForUpdate(Model model, Principal principal) {
        if (principal == null) {
            throw new RuntimeException("You need to authorize!");
        }
        User user = userService.findByName(principal.getName());
        UserDto userDto = userService.toDto(user);
        model.addAttribute("user", userDto);
        return "update";

    }

    @PostMapping("/update/user")
    @Transactional
    public String updateUser(UserDto userDto, Principal principal) {

        System.out.println(userDto);
        if (principal == null) {
            throw new RuntimeException("You are no authorize!");
        }
        userService.updateUser(userDto);
        return "redirect:/update";
    }

    @GetMapping("/delete/{userId}/user")
    public String deleteUserById(@PathVariable Long userId, Model model, Principal principal) {
        if (userId == null) {
            throw new RuntimeException("The user not found!");
        } else {
            userService.deleteUser(userId);
        }

        return "redirect:/allUsers";
    }


}