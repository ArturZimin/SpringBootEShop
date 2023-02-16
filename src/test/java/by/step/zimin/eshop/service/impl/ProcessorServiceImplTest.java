package by.step.zimin.eshop.service.impl;

import by.step.zimin.eshop.model.Color;
import by.step.zimin.eshop.model.Processor;
import by.step.zimin.eshop.model.ProductDetails;
import by.step.zimin.eshop.service.ProcessorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class ProcessorServiceImplTest {

    @Autowired
    private ProcessorService processorService;


    private Processor processor;

    @BeforeEach
    public void initialization(){
        this.processor = new Processor();
        this.processor.setId(1L);
        this.processor.setName("Intel core 2 Duo");
        this.processor.setCountCore(2);
    }

    @Test
    void addProcessor() {

        processorService.addProcessor(this.processor);
        Optional<Processor> processorFound=processorService.findFirstProcessorById(this.processor.getId());
        Assertions.assertNotNull(processorFound);


    }
}