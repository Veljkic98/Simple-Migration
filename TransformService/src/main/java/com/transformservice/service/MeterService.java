package com.transformservice.service;

import com.transformservice.domain.dto.MeterDto;
import com.transformservice.domain.entity.Meter;
import com.transformservice.exception.DataNotFoundException;

import java.util.List;

/**
 * Meter Service methods.
 *
 * @author veljkoplecas
 */
public interface MeterService {

    /**
     * Method to find Meter.
     *
     * @param profileId is ID of Profile to find Meter from
     * @param meterId is ID of Meter to find
     * @return Meter
     */
    Meter getById(Long profileId, Long meterId);

    /**
     * Method to get all Meters.
     *
     * @param profileId is ID of Profile to find all meters from
     * @return List of Meters
     */
    List<Meter> getAll(Long profileId);

    /**
     * Method to create and save Meter.
     *
     * @param profileId is ID of Profile to save meter for
     * @param meterDto
     * @return Meter
     */
    Meter create(Long profileId, MeterDto meterDto);

    /**
     * Method to update Meter.
     *
     * @param profileId is ID of Profile to update meter from
     * @param meterId is ID of Meter
     * @param meterDto
     * @return updated Meter
     */
    Meter update(Long profileId, Long meterId, MeterDto meterDto);

    /**
     * Method to delete Meter.
     *
     * @param profileId is ID of Profile to delete Meter from
     * @param meterId is ID of Meter to be deleted
     * @throws DataNotFoundException is Profile or Meter not found
     */
    void delete(Long profileId, Long meterId);

}
