package com.energyconsumption.controller;

import com.energyconsumption.controller.doc.FractionControllerSwagger;
import com.energyconsumption.mapper.FractionMapper;
import com.energyconsumption.domain.dto.FractionDto;
import com.energyconsumption.domain.dto.FractionsDto;
import com.energyconsumption.service.FractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/profile/{profileId}/fraction")
public class FractionController implements FractionControllerSwagger {

    private final FractionService fractionService;

    @Autowired
    public FractionController(FractionService fractionService) {
        this.fractionService = fractionService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<FractionDto>> getAllByProfile(@PathVariable Long profileId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(FractionMapper.toDtoList(fractionService.getAll(profileId)));
    }

    @Override
    @PostMapping
    public ResponseEntity<List<FractionDto>> create(@PathVariable Long profileId,
                                                    @RequestBody @Validated FractionsDto fractionsDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(FractionMapper.toDtoList(fractionService.create(profileId, fractionsDto)));
    }

    @Override
    @PutMapping
    public ResponseEntity<List<FractionDto>> update(@PathVariable Long profileId,
                                           @RequestBody FractionsDto fractionsDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(FractionMapper.toDtoList(fractionService.update(profileId, fractionsDto)));
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable Long profileId) {
        fractionService.delete(profileId);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

}
