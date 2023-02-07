package by.step.zimin.eshop.service;

import by.step.zimin.eshop.model.Processor;

import java.util.Optional;

public interface ProcessorService {
   void addProcessor(Processor processor);
   Optional<Processor> findFirstProcessorById(Long id);
}
