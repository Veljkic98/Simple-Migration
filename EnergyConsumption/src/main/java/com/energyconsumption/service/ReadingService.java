package com.energyconsumption.service;

import com.energyconsumption.domain.dto.ConsumptionDto;
import com.energyconsumption.domain.dto.ReadingsDto;
import com.energyconsumption.domain.entity.Reading;

import java.util.List;

/**
 * Meter Reading Service methods.
 *
 * @author veljkoplecas
 */
public interface ReadingService {

    ConsumptionDto getConsumption(Long profileId, Long meterId, ConsumptionDto consumptionDto);

    List<Reading> getAll(Long profileId, Long meterId);

    List<Reading> create(Long profileId, Long meterId, ReadingsDto readingsDto);

    List<Reading> update(Long profileId, Long meterId, ReadingsDto readingsDto);

    void delete(Long profileId, Long meterId);

}
