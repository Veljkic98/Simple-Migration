package com.transformservice.service.impl;

import com.transformservice.domain.entity.Fraction;
import com.transformservice.repository.FractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class FractionServiceImpl {

    private final FractionRepository fractionRepository;

    @Autowired
    public FractionServiceImpl(FractionRepository fractionRepository) {
        this.fractionRepository = fractionRepository;
    }

    public Fraction create(Fraction fraction) throws DataIntegrityViolationException {
        return fractionRepository.save(fraction);
    }

}
