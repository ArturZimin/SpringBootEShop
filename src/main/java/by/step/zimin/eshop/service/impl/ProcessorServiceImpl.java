package by.step.zimin.eshop.service.impl;

import by.step.zimin.eshop.model.Processor;
import by.step.zimin.eshop.repository.ProcessorRepository;
import by.step.zimin.eshop.service.ProcessorService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProcessorServiceImpl implements ProcessorService {


    private final ProcessorRepository processorRepository;

    @Override
    public void addProcessor(Processor processor) {
        processorRepository.save(processor);
    }
}
