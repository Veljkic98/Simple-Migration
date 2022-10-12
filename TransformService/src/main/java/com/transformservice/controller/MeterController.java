package com.transformservice.controller;

import com.transformservice.converter.MeterConverter;
import com.transformservice.domain.dto.MeterDto;
import com.transformservice.domain.entity.Meter;
import com.transformservice.service.impl.MeterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "profile/{profileId}/meter")
public class MeterController {

    private final MeterServiceImpl meterService;

    @Autowired
    public MeterController(MeterServiceImpl meterService) {
        this.meterService = meterService;
    }

    @GetMapping
    public ResponseEntity<List<MeterDto>> getAllByProfile(@PathVariable Long profileId) {
        return ResponseEntity.ok().body(MeterConverter.toDtoList(meterService.getAllByProfile(profileId)));
    }

    @PostMapping
    public ResponseEntity<MeterDto> create(@PathVariable Long profileId,
                                           @RequestBody @Validated MeterDto meterDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(MeterConverter.toDto(meterService.create(profileId, meterDto)));
    }

    @PutMapping(path = "/{meterId}")
    public ResponseEntity<MeterDto> update(@PathVariable Long profileId,
                                            @PathVariable Long meterId,
                                            @RequestBody MeterDto meterDto) {
        return ResponseEntity.ok().body(MeterConverter.toDto(meterService.update(profileId, meterId, meterDto)));
    }

    @DeleteMapping(path = "/{meterId}")
    public ResponseEntity<Void> delete(@PathVariable Long profileId,
                                       @PathVariable Long meterId) {
        meterService.delete(profileId, meterId);

        return ResponseEntity.noContent().build();
    }


}
