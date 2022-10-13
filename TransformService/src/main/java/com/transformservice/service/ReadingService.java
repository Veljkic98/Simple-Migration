package com.transformservice.service;

import com.transformservice.domain.dto.ReadingsDto;
import com.transformservice.domain.entity.Reading;

import java.util.List;

public interface ReadingService {

    List<Reading> getAll(Long profileId, Long meterId);

    List<Reading> create(Long profileId, Long meterId, ReadingsDto readingsDto);

    List<Reading> update(Long profileId, Long meterId, ReadingsDto readingsDto);

    void delete(Long profileId, Long meterId);

}
