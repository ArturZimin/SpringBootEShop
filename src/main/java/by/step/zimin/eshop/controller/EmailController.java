package by.step.zimin.eshop.controller;

import by.step.zimin.eshop.dto.UserDto;
import by.step.zimin.eshop.response.Response;
import by.step.zimin.eshop.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;

@RestController
@RequestMapping("/email")
public class EmailController {

    private static final Logger LOG = LoggerFactory.getLogger(EmailController.class);



    @Autowired
    private EmailService emailService;



    @PostMapping(value = "/confirm")     //http://localhost:8080/email/simple/az70019148@gmail.com
    public Response confirmEmail(@RequestBody UserDto userDto) {
        System.out.println(userDto);
        try {
            emailService.sendEmail(userDto);
        } catch (MailException mailException) {
//            LOG.error("Error while sending out email..{}", mailException.getStackTrace());
            return new Response(500, "Error while sending out email..{}",null);
        }

        return new Response(200, "The email was sent , check your email and confirm it, go to the reference!",null);
    }



    @GetMapping(value = "/attachment/{user-email}")   //http://localhost:8080/email/attachment/az70019148@gmail.com
    public @ResponseBody
    ResponseEntity sendEmailWithThanksForOrder(@PathVariable("user-email") String email) {

        try {
            emailService.sendEmailWithAttachment(email, "Order Confirmation",
                    "F:\\HibernateSpring\\Spring projects\\eshop\\src\\main\\java\\by\\step\\zimin\\eshop\\file\\thanksForOrder.txt");
        } catch (FileNotFoundException | MessagingException mailException) {
//            LOG.error("Error while sending out email..{}", mailException.getStackTrace());
            return new ResponseEntity<>("Unable to send email", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Please check your inbox for order confirmation", HttpStatus.OK);
    }
}

