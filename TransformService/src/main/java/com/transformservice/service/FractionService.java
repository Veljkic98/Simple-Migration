package com.transformservice.service;

import com.transformservice.domain.dto.FractionsDto;
import com.transformservice.domain.entity.Fraction;

import java.util.List;

/**
 * Fraction Service methods.
 *
 * @author veljkoplecas
 */
public interface FractionService {

    /**
     * Method to get Fraction by ID.
     *
     * @param profileId is Profile ID
     * @param fractionId is Fraction Id
     * @return Fraction
     */
    Fraction getById(Long profileId, Long fractionId);

    /**
     * 
     * @param profileId
     * @return
     */
    List<Fraction> getAllByProfile(Long profileId);

    List<Fraction> create(Long profileId, FractionsDto fractionsDto);

    List<Fraction> update(Long profileId, FractionsDto fractionDto);

    void delete(Long profileId);

}
