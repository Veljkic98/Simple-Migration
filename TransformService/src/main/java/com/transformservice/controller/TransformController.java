package com.transformservice.controller;

import com.opencsv.exceptions.CsvValidationException;
import com.transformservice.service.TransformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class TransformController {

    private TransformService transformService;

    @Autowired
    public TransformController(TransformService transformService) {
        this.transformService = transformService;
    }

    @GetMapping(path = "/parse")
    public ResponseEntity<Void> start() throws IOException {
        transformService.proceed();

        return ResponseEntity.ok().build();
    }

}
