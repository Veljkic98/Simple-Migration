package com.energyconsumption.mapper;

import com.energyconsumption.domain.dto.MeterDto;
import com.energyconsumption.domain.entity.Meter;

import java.util.ArrayList;
import java.util.List;

public class MeterMapper {

    private MeterMapper() {
        // class instantiation is not allowed
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
