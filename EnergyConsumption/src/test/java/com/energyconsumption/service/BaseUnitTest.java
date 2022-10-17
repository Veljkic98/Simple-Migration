package com.energyconsumption.service;

import com.energyconsumption.domain.dto.FractionsDto;
import com.energyconsumption.domain.entity.Fraction;
import com.energyconsumption.domain.entity.Profile;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static com.energyconsumption.util.TestConstants.*;

@ExtendWith(MockitoExtension.class)
public abstract class BaseUnitTest {

    /**
     * Method to create List of Fractions by months.
     *
     * @param profileId is ID of Profile
     * @return List of Fractions
     */
    protected List<Fraction> createFractions(Profile profile) {
        List<Fraction> fractions = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            fractions.add(createFraction(profile, i));
        }

        return fractions;
    }

    /**
     * method to create new Fraction.
     *
     * @param profile
     * @param idx is both month and fraction index
     * @return new Fraction
     */
    protected static Fraction createFraction(Profile profile, int idx) {
        return Fraction.Builder.newInstance()
                .value(ARRAY_OF_FRACTIONS[idx])
                .month(ARRAY_OF_MONTHS[idx])
                .profile(profile)
                .build();
    }

    protected static Fraction createFraction(Profile profile) {
        return Fraction.Builder.newInstance()
                .value(ARRAY_OF_FRACTIONS[1])
                .month(ARRAY_OF_MONTHS[1])
                .profile(profile)
                .build();
    }

    protected Profile createProfile(Long profileId, String profileName) {
        return Profile.Builder.newInstance()
                .id(profileId)
                .name(profileName)
                .build();
    }

    protected FractionsDto createFractionsDto() {
        FractionsDto fractionsDto = new FractionsDto();

        fractionsDto.setJanFraction(ARRAY_OF_FRACTIONS[0]);
        fractionsDto.setFebFraction(ARRAY_OF_FRACTIONS[1]);
        fractionsDto.setMarFraction(ARRAY_OF_FRACTIONS[2]);
        fractionsDto.setAprFraction(ARRAY_OF_FRACTIONS[3]);
        fractionsDto.setMayFraction(ARRAY_OF_FRACTIONS[4]);
        fractionsDto.setJunFraction(ARRAY_OF_FRACTIONS[5]);
        fractionsDto.setJulFraction(ARRAY_OF_FRACTIONS[6]);
        fractionsDto.setAvgFraction(ARRAY_OF_FRACTIONS[7]);
        fractionsDto.setSepFraction(ARRAY_OF_FRACTIONS[8]);
        fractionsDto.setOctFraction(ARRAY_OF_FRACTIONS[9]);
        fractionsDto.setNovFraction(ARRAY_OF_FRACTIONS[10]);
        fractionsDto.setDecFraction(ARRAY_OF_FRACTIONS[11]);

        return fractionsDto;
    }

    protected FractionsDto createInvalidFractionsDto() {
        FractionsDto fractionsDto = new FractionsDto();

        fractionsDto.setJanFraction(ARRAY_OF_FRACTIONS[0]);
        fractionsDto.setFebFraction(ARRAY_OF_FRACTIONS[1]);
        fractionsDto.setMarFraction(ARRAY_OF_FRACTIONS[2]);
        fractionsDto.setAprFraction(ARRAY_OF_FRACTIONS[3]);
        fractionsDto.setMayFraction(0.8);
        fractionsDto.setJunFraction(ARRAY_OF_FRACTIONS[5]);
        fractionsDto.setJulFraction(ARRAY_OF_FRACTIONS[6]);
        fractionsDto.setAvgFraction(ARRAY_OF_FRACTIONS[7]);
        fractionsDto.setSepFraction(ARRAY_OF_FRACTIONS[8]);
        fractionsDto.setOctFraction(ARRAY_OF_FRACTIONS[9]);
        fractionsDto.setNovFraction(ARRAY_OF_FRACTIONS[10]);
        fractionsDto.setDecFraction(ARRAY_OF_FRACTIONS[11]);

        return fractionsDto;
    }

    protected FractionsDto createFieldMissingFractionsDto() {
        FractionsDto fractionsDto = new FractionsDto();

        fractionsDto.setJanFraction(ARRAY_OF_FRACTIONS[0]);
        fractionsDto.setFebFraction(ARRAY_OF_FRACTIONS[1]);
        fractionsDto.setMarFraction(ARRAY_OF_FRACTIONS[2]);
        fractionsDto.setAprFraction(ARRAY_OF_FRACTIONS[3]);
        fractionsDto.setJunFraction(ARRAY_OF_FRACTIONS[5]);
        fractionsDto.setJulFraction(ARRAY_OF_FRACTIONS[6]);
        fractionsDto.setAvgFraction(ARRAY_OF_FRACTIONS[7]);
        fractionsDto.setSepFraction(ARRAY_OF_FRACTIONS[8]);
        fractionsDto.setOctFraction(ARRAY_OF_FRACTIONS[9]);
        fractionsDto.setNovFraction(ARRAY_OF_FRACTIONS[10]);
        fractionsDto.setDecFraction(ARRAY_OF_FRACTIONS[11]);

        return fractionsDto;
    }

}
