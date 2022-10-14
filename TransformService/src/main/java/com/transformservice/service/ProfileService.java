package com.transformservice.service;

import com.transformservice.domain.dto.ProfileDto;
import com.transformservice.domain.entity.Profile;

import java.util.List;

public interface ProfileService {

    Profile getById(Long profileId);

    List<Profile> getAll();

    Profile create(ProfileDto profileDto);

    Profile update(Long profileId, ProfileDto profileDto);

    void delete(Long profileId);

}
