package by.step.zimin.eshop.service.impl;

import by.step.zimin.eshop.model.Role;
import by.step.zimin.eshop.model.User;
import by.step.zimin.eshop.model.VerificationToken;
import by.step.zimin.eshop.service.VerificationTokenService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@ActiveProfiles("dev")
class VerificationTokenServiceImplTest {

    @Autowired
    private VerificationTokenService verificationTokenService;

    User user;
    String token;

    @BeforeEach
    public void initialization() {
        token = UUID.randomUUID().toString();

        user = new User();
        user.setEnable(false);
        user.setUsername("Artur V");
        user.setPassword("4444");
        user.setEmail("hgghgh@jhj.com");
        user.setAddress("Minsk Angarskaya 12");
        user.setRole(Role.USER);
    }

    @Test
    void findByToken() {
        verificationTokenService.save(user, this.token);
        VerificationToken tokenFined = verificationTokenService.findByToken(this.token);
        Assertions.assertEquals(tokenFined.getToken(),this.token);
        Assertions.assertEquals(tokenFined.getUser(),user);
    }

    @Test
    void findByUser() {
        verificationTokenService.save(user, this.token);
       VerificationToken verificationToken= verificationTokenService.findByUser(user);
        Assertions.assertEquals(verificationToken.getToken(),this.token);
        Assertions.assertEquals(verificationToken.getUser(),user);
    }

    @Test
    void save() {
        verificationTokenService.save(user, this.token);
        VerificationToken tokenFined = verificationTokenService.findByToken(this.token);
        Assertions.assertEquals(tokenFined.getToken(),this.token);
    }

    @Test
    void findUserByEmail() {
        verificationTokenService.save(user, this.token);
       Optional<User> userOptional= verificationTokenService.findUserByEmail(user.getEmail());
       Assertions.assertNotNull(userOptional);
       Assertions.assertEquals(userOptional.get().getEmail(),user.getEmail());
       Assertions.assertEquals(userOptional.get().getUsername(),user.getUsername());
    }
}