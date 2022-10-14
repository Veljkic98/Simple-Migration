package com.transformservice.controller;

import com.transformservice.domain.dto.*;
import com.transformservice.mapper.ReadingMapper;
import com.transformservice.service.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/profile/{profileId}/meter/{meterId}/reading")
public class ReadingController {

    private final ReadingService readingService;

    @Autowired
    public ReadingController(ReadingService readingService) {
        this.readingService = readingService;
    }

    @GetMapping
    public ResponseEntity<List<ReadingDto>> getAll(@PathVariable Long profileId,
                                                   @PathVariable Long meterId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ReadingMapper.toDtoList(readingService.getAll(profileId, meterId)));
    }

    @PostMapping
    public ResponseEntity<List<ReadingDto>> create(@PathVariable Long profileId,
                                             @PathVariable Long meterId,
                                             @RequestBody ReadingsDto readingsDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ReadingMapper.toDtoList(readingService.create(profileId, meterId, readingsDto)));
    }

    @PutMapping
    public ResponseEntity<List<ReadingDto>> update(@PathVariable Long profileId,
                                                    @PathVariable Long meterId,
                                                    @RequestBody ReadingsDto readingsDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ReadingMapper.toDtoList(readingService.update(profileId, meterId, readingsDto)));
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable Long profileId,
                                       @PathVariable Long meterId) {
        readingService.delete(profileId, meterId);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

}
