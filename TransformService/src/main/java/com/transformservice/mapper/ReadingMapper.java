package com.transformservice.mapper;

import com.transformservice.domain.dto.ReadingDto;
import com.transformservice.domain.entity.Reading;

import java.util.ArrayList;
import java.util.List;

public class ReadingMapper {

    private ReadingMapper() {
        // class instantiation is not allowed
    }

    private static ReadingDto toDto(Reading reading) {
        return ReadingDto.Builder.newInstance()
                .id(reading.getId())
                .value(reading.getValue())
                .month(reading.getMonth())
                .build();
    }

    public static List<ReadingDto> toDtoList(List<Reading> readings) {
        List<ReadingDto> readingDtos = new ArrayList<>();

        readings.forEach(r -> {
            readingDtos.add(toDto(r));
        });

        return readingDtos;
    }

}
