package com.profileservice.assertation;

import com.profileservice.domain.dto.ProfileDto;
import com.profileservice.domain.entity.Profile;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProfileAssertation {

    /**
     * Assert equality of 2 list with same order.
     *
     * @param profiles is list of Profile entities
     * @param obtainedProfilesDto is list of ProfileDto dtos
     */
    public static void assertProfiles(List<Profile> profiles, List<ProfileDto> obtainedProfilesDto) {
        assertEquals(profiles.size(), obtainedProfilesDto.size());

        for (int i = 0; i < profiles.size(); i++) {
            assertEquals(profiles.get(i).getId(), obtainedProfilesDto.get(i).getId());
            assertEquals(profiles.get(i).getName(), obtainedProfilesDto.get(i).getName());
            assertEquals(profiles.get(i).getFraction(), obtainedProfilesDto.get(i).getFraction());
            assertEquals(profiles.get(i).getMeterReading(), obtainedProfilesDto.get(i).getMeterReading());
            assertEquals(profiles.get(i).getMonth(), obtainedProfilesDto.get(i).getMonth());
        }
    }

    /**
     * Assert equality of 2 profiles.
     *
     * @param profile is Profile entity
     * @param expectedProfileDto is ProfileDto dto
     */
    public static void assertProfile(Profile profile, ProfileDto expectedProfileDto) {
        assertNotNull(expectedProfileDto);
        assertEquals(profile.getId(), expectedProfileDto.getId());
        assertEquals(profile.getName(), expectedProfileDto.getName());
        assertEquals(profile.getFraction(), expectedProfileDto.getFraction());
        assertEquals(profile.getMeterReading(), expectedProfileDto.getMeterReading());
        assertEquals(profile.getMonth(), expectedProfileDto.getMonth());
    }

}
