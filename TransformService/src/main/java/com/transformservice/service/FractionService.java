package com.transformservice.service;

import com.transformservice.domain.entity.Fraction;
import com.transformservice.repository.FractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FractionService {

    private final FractionRepository fractionRepository;

    @Autowired
    public FractionService(FractionRepository fractionRepository) {
        this.fractionRepository = fractionRepository;
    }

    public Fraction create(Fraction fraction) {
        return fractionRepository.save(fraction);
    }

}
