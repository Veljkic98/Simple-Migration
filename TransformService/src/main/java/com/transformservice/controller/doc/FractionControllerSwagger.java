package com.transformservice.controller.doc;

import com.transformservice.domain.dto.FractionDto;
import com.transformservice.domain.dto.FractionsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface FractionControllerSwagger {

    @Operation(summary = "Get all Fractions by Profile Id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation.",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = FractionDto.class)))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Profile not exists",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(mediaType = "application/json"))
    })
    ResponseEntity<List<FractionDto>> getAllByProfile(@PathVariable Long profileId);


    @Operation(summary = "Save new Fraction")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Successful operation.",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = FractionDto.class)))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Request body fields are invalid or missing",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(
                    responseCode = "404",
                    description = "Profile not found.",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(mediaType = "application/json"))
    })
    ResponseEntity<List<FractionDto>> create(@PathVariable Long profileId,
                                                    @RequestBody @Validated FractionsDto fractionsDto);

    @Operation(summary = "Update Fractions")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation.",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = FractionDto.class)))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Request body fields are invalid or missing",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(
                    responseCode = "404",
                    description = "Profile or Fraction not found.",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(mediaType = "application/json"))
    })
    ResponseEntity<List<FractionDto>> update(@PathVariable Long profileId,
                                                    @RequestBody FractionsDto fractionsDto);

    @Operation(summary = "Delete Fractions")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Successful operation."),
            @ApiResponse(
                    responseCode = "404",
                    description = "Profile or Fraction not found.",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(mediaType = "application/json"))
    })
    ResponseEntity<Void> delete(@PathVariable Long profileId);

}
