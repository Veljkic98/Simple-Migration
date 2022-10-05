package com.transformservice.controller;

import com.transformservice.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransformController {

    private ProfileService profileService;

    @Autowired
    public TransformController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping(path = "/start")
    public ResponseEntity<Void> start() throws Exception {
        profileService.proceed();
        return ResponseEntity.ok().build();
    }
}
