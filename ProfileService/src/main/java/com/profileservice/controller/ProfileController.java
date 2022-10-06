package com.profileservice.controller;

import com.profileservice.domain.dto.ConsumptionDto;
import com.profileservice.domain.dto.ProfileDto;
import com.profileservice.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/profile")
public class ProfileController {

    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<ProfileDto> getProfile(@PathVariable Long profileId) {
        return ResponseEntity.ok().body(profileService.getOne(profileId));
    }

    @GetMapping
    public ResponseEntity<List<ProfileDto>> getProfilesByName(@RequestParam(value = "profileName") String profileName) {
        return ResponseEntity.ok().body(profileService.getAllByName(profileName));
    }

    @GetMapping("/consumption")
    public ResponseEntity<ConsumptionDto> getConsumptionsForMonths(@RequestParam(value = "profileName") String profileName,
                                                                   @RequestParam(value = "monthFrom") String monthFrom,
                                                                   @RequestParam(value = "monthTo") String monthTo) {
        return ResponseEntity.ok().body(profileService.getConsumptionForMonths(profileName, monthFrom, monthTo));
    }

}
