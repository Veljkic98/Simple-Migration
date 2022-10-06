package com.transformservice.controller;

import com.opencsv.exceptions.CsvValidationException;
import com.transformservice.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class TransformController {

    private ProfileService profileService;

    @Autowired
    public TransformController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping(path = "/start")
    public ResponseEntity<Void> start() throws IOException, CsvValidationException {
        profileService.proceed();
        return ResponseEntity.ok().build();
    }

}
