package by.step.zimin.eshop.service.impl;

import by.step.zimin.eshop.dto.UserDto;
import by.step.zimin.eshop.model.User;
import by.step.zimin.eshop.model.VerificationToken;
import by.step.zimin.eshop.service.EmailService;
import by.step.zimin.eshop.service.VerificationTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import java.io.FileNotFoundException;

@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger log= LoggerFactory.getLogger(EmailServiceImpl.class);


    private JavaMailSender javaMailSender;
    private final VerificationTokenService verificationTokenService;
    private final TemplateEngine templateEngine;

    @Autowired
    public EmailServiceImpl( VerificationTokenService verificationTokenService,JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.verificationTokenService = verificationTokenService;

        this.templateEngine = templateEngine;
    }

    public void sendEmail(UserDto userDto) {
        StringBuilder sb = new StringBuilder("Hello : " + userDto.getUsername() + " ," +
                " for registration go to the link: " +
                "http://localhost:8080/email/confirm/registration?username=" + userDto.getUsername() + "&password=" + userDto.getPassword() + "&email=" + userDto.getEmail() + "&address=" + userDto.getAddress() + "&phone=" + userDto.getPhone());
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("artikris50@gmail.com");//от кого письмо берет значение из метода InternetAddress.getLocalAddress.
        message.setTo(userDto.getEmail());  // на адрес
        message.setText(sb.toString()); //message
        message.setSubject("Email Confirmation");


        javaMailSender.send(message);


    }

    @Override
    public void sendHtmlMail(User user) throws MessagingException {
        VerificationToken verificationToken = verificationTokenService.findByUser(user);
        //has if the user has a token
        if(verificationToken!=null){
            String token=verificationToken.getToken();//generate random token
            Context context =new Context();//package org.thymeleaf.context;
            context.setVariable("title","Verify your email address");
            context.setVariable("link","http://localhost:8080/account/activation?token="+token);
            String body=templateEngine.process("verification",context);//form page for send

            //Send the verification email (form email massage with body)
            MimeMessage message=javaMailSender.createMimeMessage();//create reference on the message
            MimeMessageHelper helper=new MimeMessageHelper(message,true);
            helper.setTo(user.getEmail());
            helper.setSubject("Email address verification");
            helper.setText(body,true);
            javaMailSender.send(message);
        }
    }


    @Override
    public void sendEmailWithAttachment(String toAddress, String subject, String attachment) throws MessagingException, FileNotFoundException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
        messageHelper.setTo(toAddress);
        messageHelper.setSubject(subject);
        FileSystemResource file = new FileSystemResource(ResourceUtils.getFile(attachment));
        messageHelper.addAttachment("thanksForOrder", file);
        javaMailSender.send(mimeMessage);

    }
}
