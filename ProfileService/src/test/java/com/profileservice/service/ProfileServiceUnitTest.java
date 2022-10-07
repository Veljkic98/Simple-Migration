package com.profileservice.service;

import com.profileservice.domain.dto.ConsumptionDto;
import com.profileservice.domain.dto.ProfileDto;
import com.profileservice.domain.entity.Profile;
import com.profileservice.exception.DataNotFoundException;
import com.profileservice.repository.ProfileRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static com.profileservice.assertation.ProfileAssertation.*;
import static com.profileservice.util.TestConstants.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

class ProfileServiceUnitTest extends BaseUnitTest {

    @Mock
    private ProfileRepository profileRepository;

    @InjectMocks
    private ProfileService profileService;

    @Test
    void getOne_profileExists_returnProfile() {
        Profile profile = createProfile();
        when(profileRepository.findById(PROFILE_ID_1)).thenReturn(Optional.of(profile));

        ProfileDto obtainedProfileDto = profileService.getOne(PROFILE_ID_1);

        assertProfile(profile, obtainedProfileDto);
    }

    @Test
    void getOne_profileNotExists_throwException() {
        when(profileRepository.findById(any())).thenReturn(Optional.empty());

        Exception exception = assertThrows(DataNotFoundException.class,
                () -> profileService.getOne(PROFILE_ID_1));
        assertTrue(exception.getMessage().contains(DATA_NOT_FOUND_EXCEPTION_MESSAGE));
    }

    @Test
    void getAllByName_profilesExist_returnProfiles() {
        List<Profile> profiles = createGroupOfProfiles();
        when(profileRepository.findAllByName(PROFILE_NAME_1)).thenReturn(profiles);

        List<ProfileDto> obtainedProfilesDto = profileService.getAllByName(PROFILE_NAME_1);

        assertProfiles(profiles, obtainedProfilesDto);
    }

    @Test
    void getAllByName_profilesNotExist_throwException() {
        when(profileRepository.findAllByName(any())).thenReturn(List.of());

        Exception exception = assertThrows(DataNotFoundException.class,
                () -> profileService.getAllByName(PROFILE_NAME_1));
        assertTrue(exception.getMessage().contains(DATA_NOT_FOUND_EXCEPTION_MESSAGE));
    }

    @Test
    void getConsumptionForMonths_validFractionsAndMeterReadings_returnConsumption() {
        List<Profile> profiles = createGroupOfProfiles();
        when(profileRepository.findAllByName(PROFILE_NAME_1)).thenReturn(profiles);

        ConsumptionDto obtainedConsumption = profileService.getConsumptionForMonths(PROFILE_NAME_1, PROFILE_MONTH_2, PROFILE_MONTH_2);

        assertEquals(29, obtainedConsumption.getConsumption());
    }

    @Test
    void getConsumptionForMonths_profilesNotExist_returnConsumption() {
        when(profileRepository.findAllByName(any())).thenReturn(List.of());

        Exception exception = assertThrows(DataNotFoundException.class,
                () -> profileService.getConsumptionForMonths(PROFILE_NAME_1, PROFILE_MONTH_2, PROFILE_MONTH_2));
        assertTrue(exception.getMessage().contains(DATA_NOT_FOUND_EXCEPTION_MESSAGE));
    }

}
