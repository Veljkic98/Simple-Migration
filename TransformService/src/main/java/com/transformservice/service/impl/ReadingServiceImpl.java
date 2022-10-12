package com.transformservice.service.impl;

import com.transformservice.domain.entity.Reading;
import com.transformservice.repository.ReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReadingServiceImpl {

    private final ReadingRepository readingRepository;

    @Autowired
    public ReadingServiceImpl(ReadingRepository readingRepository) {
        this.readingRepository = readingRepository;
    }

    public Reading create(Reading reading) {
        return readingRepository.save(reading);
    }

}
