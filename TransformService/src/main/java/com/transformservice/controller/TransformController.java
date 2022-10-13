package com.transformservice.controller;

import com.transformservice.service.impl.TransformServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class TransformController {

    private final TransformServiceImpl transformService;

    @Autowired
    public TransformController(TransformServiceImpl transformService) {
        this.transformService = transformService;
    }

    @PostMapping(path = "/upload")
    public ResponseEntity<Void> uploadData(@RequestParam("profiles") MultipartFile fileProfiles,
                                           @RequestParam("meterReadings") MultipartFile fileMeterReadings)
            throws IOException {
        transformService.parse(fileProfiles, fileMeterReadings);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
