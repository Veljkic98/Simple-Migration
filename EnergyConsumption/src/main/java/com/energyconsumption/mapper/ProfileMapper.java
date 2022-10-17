package com.energyconsumption.mapper;

import com.energyconsumption.domain.dto.ProfileDto;
import com.energyconsumption.domain.entity.Profile;

import java.util.ArrayList;
import java.util.List;

public class ProfileMapper {

    private ProfileMapper() {
        // class instantiation is not allowed
    }

    public static ProfileDto toDto(Profile profile) {
        return ProfileDto.Builder.newInstance()
                .id(profile.getId())
                .name(profile.getName())
                .build();
    }

    public static List<ProfileDto> toDtoList(List<Profile> profiles) {
        List<ProfileDto> profileDtos = new ArrayList<>();

        profiles.forEach(p -> profileDtos.add(toDto(p)));

        return profileDtos;
    }

}
