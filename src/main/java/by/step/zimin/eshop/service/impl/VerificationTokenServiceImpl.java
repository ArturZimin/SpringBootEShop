package by.step.zimin.eshop.service.impl;

import by.step.zimin.eshop.model.User;
import by.step.zimin.eshop.model.VerificationToken;
import by.step.zimin.eshop.repository.UserRepository;
import by.step.zimin.eshop.repository.VerificationTokenRepository;
import by.step.zimin.eshop.service.VerificationTokenService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Optional;


@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {

    private final VerificationTokenRepository verificationTokenRepository;
    private final UserRepository userRepository;



    public VerificationTokenServiceImpl(VerificationTokenRepository verificationTokenRepository, UserRepository userRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
        this.userRepository = userRepository;

    }

    @Transactional
    public VerificationToken findByToken(String token) {
        return verificationTokenRepository.findByToken(token);
    }

    @Transactional
    public VerificationToken findByUser(User user) {
        return verificationTokenRepository.findByUser(user);
    }

    @Transactional
    public void save(User user, String token) {
        VerificationToken verificationToken = new VerificationToken(token, user);

        //set expiry date to 24 hours
        verificationToken.setExpiryDate(calculateExpiryDate(24 * 60));
        verificationTokenRepository.save(verificationToken);
    }

    @Transactional
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }


    private Timestamp calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Timestamp(cal.getTime().getTime());
    }

}
