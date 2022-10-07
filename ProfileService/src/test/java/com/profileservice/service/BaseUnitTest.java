package com.profileservice.service;

import com.profileservice.domain.entity.Profile;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.profileservice.util.TestConstants.*;

@ExtendWith(MockitoExtension.class)
public abstract class BaseUnitTest {

    protected Profile createProfile() {
        return Profile.Builder.newInstance()
                .id(PROFILE_ID_1)
                .fraction(PROFILE_FRACTION_1)
                .meterReading(PROFILE_METER_READING_1)
                .name(PROFILE_NAME_1)
                .month(PROFILE_MONTH_1)
                .build();
    }

    protected Profile createProfile(Long id, String name, String month, Double fraction, Integer meterReading) {
        return Profile.Builder.newInstance()
                .id(id)
                .fraction(fraction)
                .meterReading(meterReading)
                .name(name)
                .month(month)
                .build();
    }

    protected List<Profile> createGroupOfProfiles() {
        Profile profile1 = createProfile(PROFILE_ID_1, PROFILE_NAME_1, PROFILE_MONTH_1, PROFILE_FRACTION_1, PROFILE_METER_READING_1);
        Profile profile2 = createProfile(PROFILE_ID_2, PROFILE_NAME_1, PROFILE_MONTH_2, PROFILE_FRACTION_2, PROFILE_METER_READING_2);

        return List.of(profile1, profile2);
    }

}
