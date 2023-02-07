package by.step.zimin.eshop.service.impl;

import by.step.zimin.eshop.model.Processor;
import by.step.zimin.eshop.repository.ProcessorRepository;
import by.step.zimin.eshop.service.ProcessorService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProcessorServiceImpl implements ProcessorService {

    private static final Logger log= LoggerFactory.getLogger(ProcessorServiceImpl.class);


    private final ProcessorRepository processorRepository;

    @Override
    @Transactional
    public void addProcessor(Processor processor) {
        processorRepository.save(processor);
    }

    @Override
    @Transactional
    public Optional<Processor> findFirstProcessorById(Long id) {
        return processorRepository.findById(id);
    }
}
