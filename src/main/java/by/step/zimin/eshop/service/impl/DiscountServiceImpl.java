package by.step.zimin.eshop.service.impl;

import by.step.zimin.eshop.model.Discount;
import by.step.zimin.eshop.repository.DiscountRepository;
import by.step.zimin.eshop.service.DiscountService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class DiscountServiceImpl implements DiscountService {


    @Autowired
    private DiscountRepository discountRepository;

    @Override
    public Discount save(Discount discount) {
        log.info("The discount was saved in db! " + discount);
        return discountRepository.save(discount);
    }
}
