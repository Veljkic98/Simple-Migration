package com.transformservice.service;

import com.transformservice.domain.entity.Meter;
import com.transformservice.repository.MeterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeterService {

    private final MeterRepository meterRepository;

    @Autowired
    public MeterService(MeterRepository meterRepository) {
        this.meterRepository = meterRepository;
    }

    public Meter create(Meter meter) throws DataIntegrityViolationException {
        return meterRepository.save(meter);
    }

    public List<Meter> getAll() {
        return meterRepository.findAll();
    }
}
