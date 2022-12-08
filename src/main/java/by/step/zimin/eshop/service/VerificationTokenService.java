package by.step.zimin.eshop.service;

import by.step.zimin.eshop.model.User;
import by.step.zimin.eshop.model.VerificationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface VerificationTokenService {
    void save(User user, String token);

    VerificationToken findByUser(User user);

    VerificationToken findByToken(String token);
    Optional<User> findUserByEmail(String email);
}
