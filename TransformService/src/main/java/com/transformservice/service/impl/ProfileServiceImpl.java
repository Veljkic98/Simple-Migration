package com.transformservice.service.impl;

import com.transformservice.domain.entity.Profile;
import com.transformservice.exception.DataNotFoundException;
import com.transformservice.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileServiceImpl {

    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public Profile create(Profile profile) throws DataIntegrityViolationException {
        return profileRepository.save(profile);
    }

    public Profile getById(Long id) {
        Optional<Profile> profile;

        if (!(profile = profileRepository.findById(id)).isPresent()) {
            throw new DataNotFoundException(String.format("Profile with id: %s not found.", id));
        }

        return profile.get();
    }

}
