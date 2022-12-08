package by.step.zimin.eshop.service;

import by.step.zimin.eshop.dto.UserDto;
import by.step.zimin.eshop.model.User;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;

public interface EmailService {
    void sendEmail(UserDto userDto);

    void sendHtmlMail(User user) throws MessagingException;

    void sendEmailWithAttachment(String toAddress, String subject,  String attachment) throws MessagingException, FileNotFoundException;
}
