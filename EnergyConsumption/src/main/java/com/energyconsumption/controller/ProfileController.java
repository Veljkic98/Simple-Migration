package com.energyconsumption.controller;

import com.energyconsumption.mapper.ProfileMapper;
import com.energyconsumption.domain.dto.ProfileDto;
import com.energyconsumption.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping(path = "/{profileId}")
    public ResponseEntity<ProfileDto> get(@PathVariable Long profileId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ProfileMapper.toDto(profileService.getById(profileId)));
    }

    @GetMapping
    public ResponseEntity<List<ProfileDto>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ProfileMapper.toDtoList(profileService.getAll()));
    }

    @PostMapping
    public ResponseEntity<ProfileDto> create(@RequestBody ProfileDto profileDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ProfileMapper.toDto(profileService.create(profileDto)));
    }

    @PutMapping(path = "/{profileId}")
    public ResponseEntity<ProfileDto> update(@PathVariable Long profileId,
                                             @RequestBody ProfileDto profileDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ProfileMapper.toDto(profileService.update(profileId, profileDto)));
    }

    @DeleteMapping(path = "/{profileId}")
    public ResponseEntity<Void> update(@PathVariable Long profileId) {
        profileService.delete(profileId);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

}
