package by.step.zimin.eshop.service;

import by.step.zimin.eshop.model.User;
import by.step.zimin.eshop.model.VerificationToken;
import org.springframework.stereotype.Service;

@Service
public interface VerificationTokenService {
    void save(User user, String token);

    VerificationToken findByUser(User user);

    VerificationToken findByToken(String token);
}
