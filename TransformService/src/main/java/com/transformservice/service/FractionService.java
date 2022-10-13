package com.transformservice.service;

import com.transformservice.domain.dto.FractionDto;
import com.transformservice.domain.dto.FractionsDto;
import com.transformservice.domain.entity.Fraction;

import java.util.List;

public interface FractionService {

    List<Fraction> getAllByProfile(Long profileId);

    Fraction getById(Long profileId, Long fractionId);

    List<Fraction> create(Long profileId, FractionsDto fractionsDto);

    List<Fraction> update(Long profileId, FractionsDto fractionDto);

    void delete(Long profileId);

}
