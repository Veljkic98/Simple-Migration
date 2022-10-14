package com.transformservice.service;

import com.transformservice.domain.dto.FractionsDto;
import com.transformservice.domain.entity.Fraction;
import com.transformservice.domain.entity.Profile;
import com.transformservice.exception.DataMissingException;
import com.transformservice.exception.InvalidDataException;
import com.transformservice.repository.FractionRepository;
import com.transformservice.service.impl.FractionServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static com.transformservice.util.TestConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class FractionServiceTest extends BaseUnitTest {

    @Mock
    private FractionRepository fractionRepository;

    @Mock
    private ProfileService profileService;

    @InjectMocks
    private FractionServiceImpl fractionService;

    @Test
    void getAllByProfileId_profileIdOk_ListOfFractions() {
        Profile profile = createProfile(PROFILE_ID_1, PROFILE_NAME_1);
        List<Fraction> fractions = createFractions(profile);

        when(fractionRepository.findAllByProfileId(PROFILE_ID_1)).thenReturn(fractions);

        List<Fraction> obtainedFractions = fractionService.getAll(PROFILE_ID_1);

        assertFractions(obtainedFractions, fractions);
    }

    @Test
    void getAllByProfileId_profileNotFound_emptyList() {
        when(fractionRepository.findAllByProfileId(any())).thenReturn(List.of());

        List<Fraction> obtainedFractions = fractionService.getAll(PROFILE_ID_1);

        assertEquals(0, obtainedFractions.size());
    }

    @Test
    void create_fractionsDtoOk_fractions() {
        Profile profile = createProfile(PROFILE_ID_1, PROFILE_NAME_1);

        // integrity of fractions and fractionsDto is OK
        List<Fraction> fractions = createFractions(profile);
        FractionsDto fractionsDto = createFractionsDto();

        when(profileService.getById(PROFILE_ID_1)).thenReturn(profile);
        when(fractionRepository.saveAll(any())).thenReturn(fractions);

        List<Fraction> obtainedFractions = fractionService.create(PROFILE_ID_1, fractionsDto);

        assertFractions(obtainedFractions, fractions);
    }

    @Test
    void create_fractionsDtoInvalid_exception() {
        FractionsDto fractionsDto = createInvalidFractionsDto();

        Exception exception = assertThrows(InvalidDataException.class,
                () -> fractionService.create(PROFILE_ID_1, fractionsDto));

        assertTrue(exception.getMessage().contains("must be 1"));
    }

    @Test
    void create_fractionsDtoFieldNull_exception() {
        FractionsDto fractionsDto = createFieldMissingFractionsDto();

        Exception exception = assertThrows(DataMissingException.class,
                () -> fractionService.create(PROFILE_ID_1, fractionsDto));

        assertTrue(exception.getMessage().contains("must not be null"));
    }

    @Test
    void update_fractionsDtoOk_fractions() {
        Profile profile = createProfile(PROFILE_ID_1, PROFILE_NAME_1);

        // integrity of fractions and fractionsDto is OK
        List<Fraction> fractions = createFractions(profile);
        FractionsDto fractionsDto = createFractionsDto();

        when(fractionRepository.saveAll(any())).thenReturn(fractions);
        when(fractionRepository.findAllByProfileId(PROFILE_ID_1)).thenReturn(fractions);

        List<Fraction> obtainedFractions = fractionService.update(PROFILE_ID_1, fractionsDto);

        assertFractions(obtainedFractions, fractions);
    }

    @Test
    void update_fractionsDtoInvalid_exception() {
        FractionsDto fractionsDto = createInvalidFractionsDto();

        Exception exception = assertThrows(InvalidDataException.class,
                () -> fractionService.create(PROFILE_ID_1, fractionsDto));

        assertTrue(exception.getMessage().contains("must be 1"));
    }

    @Test
    void update_fractionsDtoFieldNull_exception() {
        FractionsDto fractionsDto = createFieldMissingFractionsDto();

        Exception exception = assertThrows(DataMissingException.class,
                () -> fractionService.create(PROFILE_ID_1, fractionsDto));

        assertTrue(exception.getMessage().contains("must not be null"));
    }

    private void assertFractions(List<Fraction> obtainedFractions, List<Fraction> fractions) {
        assertEquals(12, obtainedFractions.size());
        for (int i = 0; i < 12; i++) {
            assertFraction(fractions.get(i), obtainedFractions.get(i));
        }
    }

    private void assertFraction(Fraction fraction, Fraction obtainedFraction) {
            assertEquals(fraction.getId(), obtainedFraction.getId());
            assertEquals(fraction.getMonth(), obtainedFraction.getMonth());
            assertEquals(fraction.getValue(), obtainedFraction.getValue());
    }

}
