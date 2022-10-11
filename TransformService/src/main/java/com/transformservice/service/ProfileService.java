package com.transformservice.service;

import com.transformservice.domain.entity.Profile;
import com.transformservice.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public Profile create(Profile profile) {
        return profileRepository.save(profile);
        // todo: throw entityExistException
    }

    public Profile getByName(String name) {
        return profileRepository.findByName(name).get();
    }

}
