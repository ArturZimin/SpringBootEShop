package by.step.zimin.eshop.service.impl;

import by.step.zimin.eshop.model.User;
import by.step.zimin.eshop.model.VerificationToken;
import by.step.zimin.eshop.repository.UserRepository;
import by.step.zimin.eshop.repository.VerificationTokenRepository;
import by.step.zimin.eshop.service.VerificationTokenService;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Optional;


@Service
@Log4j2
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
        verificationToken.setExpiryDate(calculateExpiryDate(24 * 60));//24 hours expiryDate
        verificationTokenRepository.save(verificationToken);
        log.info("Save user with token "+verificationToken);
    }

    @Transactional
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }


    private Timestamp calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();//  // create a new calendar
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);//add 24*60=1440 minutes(24 hours)
        return new Timestamp(cal.getTime().getTime());//return time in long
    }



}
/** add 9 years:
        cal.add((Calendar.YEAR), 9);
         print the modified date and time  :
        System.out.println("" + cal.getTime());  */