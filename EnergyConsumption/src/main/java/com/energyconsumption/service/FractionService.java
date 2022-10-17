package com.energyconsumption.service;

import com.energyconsumption.domain.dto.FractionsDto;
import com.energyconsumption.domain.entity.Fraction;
import com.energyconsumption.exception.DataNotFoundException;

import java.util.List;

/**
 * Fraction Service methods.
 *
 * @author veljkoplecas
 */
public interface FractionService {

    /**
     * Method to get all Fractions by profile.
     *
     * @param profileId is Profile ID to get Fractions from
     * @return List of Fractions
     */
    List<Fraction> getAll(Long profileId);

    /**
     * Method to create and save 12 Fractions for all 12 months.
     *
     * @param profileId is Profile ID to create Fractions for
     * @param fractionsDto are fractions values for all 12 month
     * @return List of created Fractions
     */
    List<Fraction> create(Long profileId, FractionsDto fractionsDto);

    /**
     * Method to update Fractions.
     *
     * @param profileId is ID of Profile to update Fractions for
     * @param fractionDto are fractions values for all 12 month
     * @return List of updated Fractions
     */
    List<Fraction> update(Long profileId, FractionsDto fractionDto);

    /**
     * Method to delete Fractions for one year.
     *
     * @param profileId is ID of Profile to delete Fractions from
     * @throws DataNotFoundException if Profile not found
     */
    void delete(Long profileId);

}
