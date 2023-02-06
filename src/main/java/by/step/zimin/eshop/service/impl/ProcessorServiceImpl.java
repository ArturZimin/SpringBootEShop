package by.step.zimin.eshop.service.impl;

import by.step.zimin.eshop.model.Processor;
import by.step.zimin.eshop.repository.ProcessorRepository;
import by.step.zimin.eshop.service.ProcessorService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProcessorServiceImpl implements ProcessorService {

    private static final Logger log= LoggerFactory.getLogger(ProcessorServiceImpl.class);


    private final ProcessorRepository processorRepository;

    @Override
    public void addProcessor(Processor processor) {
        processorRepository.save(processor);
    }
}
