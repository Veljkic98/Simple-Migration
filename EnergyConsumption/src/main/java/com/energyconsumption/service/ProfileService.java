package com.energyconsumption.service;

import com.energyconsumption.domain.dto.ProfileDto;
import com.energyconsumption.domain.entity.Profile;

import java.util.List;

/**
 * Profile Service methods.
 *
 * @author veljkoplecas
 */
public interface ProfileService {

    Profile getById(Long profileId);

    List<Profile> getAll();

    Profile create(ProfileDto profileDto);

    Profile update(Long profileId, ProfileDto profileDto);

    void delete(Long profileId);

}
