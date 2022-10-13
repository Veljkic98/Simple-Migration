package com.transformservice.service;

import com.transformservice.domain.dto.MeterDto;
import com.transformservice.domain.entity.Meter;

import java.util.List;

public interface MeterService {

    Meter getById(Long profileId, Long meterId);

    List<Meter> getAllByProfile(Long profileId);

    Meter create(Long profileId, MeterDto meterDto);

    Meter update(Long profileId, Long meterId, MeterDto meterDto);

    void delete(Long profileId, Long meterId);

}
