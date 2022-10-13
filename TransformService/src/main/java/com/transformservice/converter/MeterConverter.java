package com.transformservice.converter;

import com.transformservice.domain.dto.MeterDto;
import com.transformservice.domain.entity.Meter;

import java.util.ArrayList;
import java.util.List;

public class MeterConverter {

    private MeterConverter() {
    }

    public static MeterDto toDto(Meter meter) {
        return MeterDto.Builder.newInstance()
                .id(meter.getId())
                .meterIdentifier(meter.getMeterIdentifier())
                .build();
    }

    public static List<MeterDto> toDtoList(List<Meter> meters) {
        List<MeterDto> meterDtos = new ArrayList<>();

        meters.forEach(m -> meterDtos.add(toDto(m)));

        return meterDtos;
    }
}
