package com.transformservice.service;

import com.transformservice.domain.dto.FractionsDto;
import com.transformservice.domain.entity.Fraction;

import java.util.List;

public interface FractionService {

    Fraction getById(Long profileId, Long fractionId);

    List<Fraction> getAllByProfile(Long profileId);

    List<Fraction> create(Long profileId, FractionsDto fractionsDto);

    List<Fraction> update(Long profileId, FractionsDto fractionDto);

    void delete(Long profileId);

}
