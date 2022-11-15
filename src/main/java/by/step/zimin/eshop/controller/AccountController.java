package by.step.zimin.eshop.controller;

import by.step.zimin.eshop.model.User;
import by.step.zimin.eshop.model.VerificationToken;
import by.step.zimin.eshop.service.UserService;
import by.step.zimin.eshop.service.VerificationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;

@Controller
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {

    @Autowired
    private final VerificationTokenService verificationTokenService;
    @Autowired
    private final UserService userService;


    @GetMapping("/activation")
    public String activation(@RequestParam("token") String token, Model model) {
        //html page activation
        VerificationToken verificationToken = verificationTokenService.findByToken(token);//find this token
        if (verificationToken == null) {
            model.addAttribute("message", "Your verification token is invalid!");
        } else {
            User user = verificationToken.getUser();

            if (!user.isEnabled()) {//if user email not activated
                Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());//current timestamp
                if (verificationToken.getExpiryDate().before(currentTimestamp)) {//check if token is expired
                    model.addAttribute("message", "Your verification token has expired!");
                } else {
                    user.setEnable(true);//if the token is valid
                    userService.save(user);//update user
                    model.addAttribute("message", "Your account was activated successfully!");
                }
            } else {
                //if account was activated
                model.addAttribute("message", "Your account has already activated!");
            }
        }
        return "activation";
    }
}
