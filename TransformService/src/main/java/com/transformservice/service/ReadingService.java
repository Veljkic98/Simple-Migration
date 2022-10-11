package com.transformservice.service;

import com.transformservice.domain.entity.Reading;
import com.transformservice.repository.ReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReadingService {

    private final ReadingRepository readingRepository;

    @Autowired
    public ReadingService(ReadingRepository readingRepository) {
        this.readingRepository = readingRepository;
    }

    public Reading create(Reading reading) {
        return readingRepository.save(reading);
    }

}
