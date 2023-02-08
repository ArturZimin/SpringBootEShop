package by.step.zimin.eshop;

import by.step.zimin.eshop.service.impl.BucketServiceImpl;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@Log4j2
public class EshopApplication {



    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(EshopApplication.class, args);


        PasswordEncoder encoder = context.getBean(PasswordEncoder.class);
        System.out.println(encoder.encode("1111"));//чтобы посмотреть пароль закодированный

//        log.trace("Trace level log message");
//        log.debug("Debug level log message");
//        log.info("Info level log message");
//        log.warn("Warn level log message");
//        log.error("Error level log message");
//
    }

}


