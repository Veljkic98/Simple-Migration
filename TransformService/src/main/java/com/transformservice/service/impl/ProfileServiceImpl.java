package com.transformservice.service.impl;

import com.transformservice.domain.dto.ProfileDto;
import com.transformservice.domain.entity.Profile;
import com.transformservice.exception.DataNotFoundException;
import com.transformservice.exception.ReferentialIntegrityConstraintViolationException;
import com.transformservice.repository.ProfileRepository;
import com.transformservice.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Profile getById(Long id) {
        Optional<Profile> profile = profileRepository.findById(id);

        if (profile.isEmpty()) {
            throw new DataNotFoundException(String.format("Profile with id: %s not found.", id));
        }

        return profile.get();
    }

    @Override
    public List<Profile> getAll() {
        return profileRepository.findAll();
    }

    @Override
    public Profile create(ProfileDto profileDto) {
        Profile profile = Profile.Builder.newInstance()
                .name(profileDto.getName())
                .build();

        return profileRepository.save(profile);
    }

    @Override
    public Profile update(Long profileId, ProfileDto profileDto) {
        Profile profile = getById(profileId);

        profile.setName(profileDto.getName());

        return profileRepository.save(profile);
    }

    @Override
    public void delete(Long profileId) {
        Profile profile = getById(profileId);

        try {
            profileRepository.delete(profile);
        } catch (DataIntegrityViolationException e) {
            throw new ReferentialIntegrityConstraintViolationException(String.format(
                    "Profile with id %s has Meters and Fractions attached, and cannot be deleted.", profileId
            ));
        }
    }

}
