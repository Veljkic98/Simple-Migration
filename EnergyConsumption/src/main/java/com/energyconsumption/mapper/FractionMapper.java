package com.energyconsumption.mapper;

import com.energyconsumption.domain.dto.FractionDto;
import com.energyconsumption.domain.entity.Fraction;

import java.util.ArrayList;
import java.util.List;

public class FractionMapper {

    private FractionMapper() {
        // class instantiation is not allowed
    }
    public static List<FractionDto> toDtoList(List<Fraction> fractions) {
        List<FractionDto> fractionDtos = new ArrayList<>();

        fractions.forEach(f -> fractionDtos.add(FractionDto.Builder.newInstance()
                .id(f.getId())
                .month(f.getMonth())
                .value(f.getValue())
                .build()));

        return fractionDtos;
    }

    public static FractionDto toDto(Fraction fraction) {
        return FractionDto.Builder.newInstance()
                .id(fraction.getId())
                .month(fraction.getMonth())
                .value(fraction.getValue())
                .build();
    }

}
