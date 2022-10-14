package com.transformservice.controller;

import com.transformservice.mapper.FractionMapper;
import com.transformservice.domain.dto.FractionDto;
import com.transformservice.domain.dto.FractionsDto;
import com.transformservice.service.FractionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/profile/{profileId}/fraction")
public class FractionController {

    private final FractionService fractionService;

    @Autowired
    public FractionController(FractionService fractionService) {
        this.fractionService = fractionService;
    }

    @GetMapping
    @Operation(summary = "Get all Fractions by Profile Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation."),
            @ApiResponse(responseCode = "404", description = "Profile not exists"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<List<FractionDto>> getAllByProfile(@PathVariable Long profileId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(FractionMapper.toDtoList(fractionService.getAllByProfile(profileId)));
    }

    @GetMapping(path = "/{fractionId}")
    @Operation(summary = "Get one Fraction by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation."),
            @ApiResponse(responseCode = "404", description = "Fraction not found."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<FractionDto> getById(@PathVariable Long profileId,
                                               @PathVariable Long fractionId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(FractionMapper.toDto(fractionService.getById(profileId, fractionId)));
    }

    @PostMapping
    @Operation(summary = "Save new Fraction")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful operation."),
            @ApiResponse(responseCode = "400", description = "Request body fields are invalid or missing"),
            @ApiResponse(responseCode = "404", description = "Profile not found."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<List<FractionDto>> create(@PathVariable Long profileId,
                                                    @RequestBody @Validated FractionsDto fractionsDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(FractionMapper.toDtoList(fractionService.create(profileId, fractionsDto)));
    }

    @PutMapping
    @Operation(summary = "Update Fractions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation."),
            @ApiResponse(responseCode = "400", description = "Request body fields are invalid or missing"),
            @ApiResponse(responseCode = "404", description = "Profile or Fraction not found."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<List<FractionDto>> update(@PathVariable Long profileId,
                                           @RequestBody FractionsDto fractionsDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(FractionMapper.toDtoList(fractionService.update(profileId, fractionsDto)));
    }

    @DeleteMapping
    @Operation(summary = "Delete Fractions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful operation."),
            @ApiResponse(responseCode = "404", description = "Profile or Fraction not found."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<Void> delete(@PathVariable Long profileId) {
        fractionService.delete(profileId);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

}
