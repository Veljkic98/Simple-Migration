package com.transformservice.service;

import com.transformservice.domain.dto.FractionDto;
import com.transformservice.domain.dto.FractionsDto;
import com.transformservice.domain.entity.Fraction;

import java.util.List;

public interface FractionService {

    List<Fraction> getAllByProfile(Long profileId);

    Fraction getById(Long profileId, Long fractionId);

    Fraction createForOneMonth(Long profileId, FractionDto fractionDto);

    Fraction createForOneMonth(Long profileId, FractionsDto fractionsDto);

    Fraction update(Long profileId, Long fractionId, FractionDto fractionDto);

    void delete(Long profileId, Long fractionId);

}
