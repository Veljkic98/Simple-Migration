package com.transformservice.service.impl;

import com.transformservice.domain.dto.FractionsDto;
import com.transformservice.domain.entity.Fraction;
import com.transformservice.domain.entity.Profile;
import com.transformservice.exception.DataMissingException;
import com.transformservice.exception.DataNotFoundException;
import com.transformservice.exception.InvalidDataException;
import com.transformservice.repository.FractionRepository;
import com.transformservice.service.FractionService;
import com.transformservice.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.transformservice.util.ApplicationConstants.*;

@Service
public class FractionServiceImpl implements FractionService {

    private final FractionRepository fractionRepository;

    private final ProfileService profileService;

    @Autowired
    public FractionServiceImpl(FractionRepository fractionRepository,
                               ProfileService profileService) {
        this.fractionRepository = fractionRepository;
        this.profileService = profileService;
    }

    @Override
    public List<Fraction> getAll(Long profileId) {
        return fractionRepository.findAllByProfileId(profileId);
    }

    @Override
    public List<Fraction> create(Long profileId, FractionsDto fractionsDto) {
        validateFractionFields(fractionsDto);
        validateFracionsEqualsOne(profileId, fractionsDto);

        Profile profile = profileService.getById(profileId);

        List<Fraction> fractions = createFractions(fractionsDto, profile);

        return fractionRepository.saveAll(fractions);
    }

    @Override
    public List<Fraction> update(Long profileId, FractionsDto fractionsDto) {
        validateFractionFields(fractionsDto);
        validateFracionsEqualsOne(profileId, fractionsDto);

        List<Fraction> fractions = getAll(profileId);

        updateFractions(fractions, fractionsDto);

        return fractionRepository.saveAll(fractions);
    }

    @Override
    public void delete(Long profileId) {
        List<Fraction> fractions = getAll(profileId);

        fractionRepository.deleteAll(fractions);
    }

    private static void validateFractionFields(FractionsDto fractionsDto) {
        if (fractionsDto.isAnyFieldNull()) {
            throw new DataMissingException("Fractions by all 12 months must not be null.");
        }
    }

    private void validateFracionsEqualsOne(Long profileId, FractionsDto fractionsDto) {
        double sum = fractionsDto.getJanFraction() +
                fractionsDto.getFebFraction() +
                fractionsDto.getMarFraction() +
                fractionsDto.getAprFraction() +
                fractionsDto.getMayFraction() +
                fractionsDto.getJunFraction() +
                fractionsDto.getJulFraction() +
                fractionsDto.getAvgFraction() +
                fractionsDto.getSepFraction() +
                fractionsDto.getOctFraction() +
                fractionsDto.getNovFraction() +
                fractionsDto.getDecFraction();

        if (sum != 1.0) {
            throw new InvalidDataException(
                    String.format("Sum of all fractions br profile with id %s must be 1.", profileId));
        }
    }

    private List<Fraction> createFractions(FractionsDto fractionsDto, Profile profile) {
        List<Fraction> fractions = new ArrayList<>();

        Map<Integer, String> months = createMapOfMonths();

        fractions.add(createFraction(months.get(0), fractionsDto.getJanFraction(), profile));
        fractions.add(createFraction(months.get(1), fractionsDto.getFebFraction(), profile));
        fractions.add(createFraction(months.get(2), fractionsDto.getMarFraction(), profile));
        fractions.add(createFraction(months.get(3), fractionsDto.getAprFraction(), profile));
        fractions.add(createFraction(months.get(4), fractionsDto.getMayFraction(), profile));
        fractions.add(createFraction(months.get(5), fractionsDto.getJunFraction(), profile));
        fractions.add(createFraction(months.get(6), fractionsDto.getJulFraction(), profile));
        fractions.add(createFraction(months.get(7), fractionsDto.getAvgFraction(), profile));
        fractions.add(createFraction(months.get(8), fractionsDto.getSepFraction(), profile));
        fractions.add(createFraction(months.get(9), fractionsDto.getOctFraction(), profile));
        fractions.add(createFraction(months.get(10), fractionsDto.getNovFraction(), profile));
        fractions.add(createFraction(months.get(11), fractionsDto.getDecFraction(), profile));

        return fractions;
    }

    private boolean fractionsEqualsOne(List<Fraction> fractions) {
        double sum = 0.0;

        for (Fraction f : fractions) {
            sum += f.getValue();
        }

        return sum == 1.0;
    }

    private void updateFractions(List<Fraction> fractions, FractionsDto fractionsDto) {
        fractions.get(findIdxOfFractionByMonth("JAN", fractions)).setValue(fractionsDto.getJanFraction());
        fractions.get(findIdxOfFractionByMonth("FEB", fractions)).setValue(fractionsDto.getFebFraction());
        fractions.get(findIdxOfFractionByMonth("MAR", fractions)).setValue(fractionsDto.getMarFraction());
        fractions.get(findIdxOfFractionByMonth("APR", fractions)).setValue(fractionsDto.getAprFraction());
        fractions.get(findIdxOfFractionByMonth("MAY", fractions)).setValue(fractionsDto.getMayFraction());
        fractions.get(findIdxOfFractionByMonth("JUN", fractions)).setValue(fractionsDto.getJunFraction());
        fractions.get(findIdxOfFractionByMonth("JUL", fractions)).setValue(fractionsDto.getJulFraction());
        fractions.get(findIdxOfFractionByMonth("AVG", fractions)).setValue(fractionsDto.getAvgFraction());
        fractions.get(findIdxOfFractionByMonth("SEP", fractions)).setValue(fractionsDto.getSepFraction());
        fractions.get(findIdxOfFractionByMonth("OCT", fractions)).setValue(fractionsDto.getOctFraction());
        fractions.get(findIdxOfFractionByMonth("NOV", fractions)).setValue(fractionsDto.getNovFraction());
        fractions.get(findIdxOfFractionByMonth("DEC", fractions)).setValue(fractionsDto.getDecFraction());
    }

    private int findIdxOfFractionByMonth(String month, List<Fraction> fractions) {
        int idx = 0;

        for (Fraction f : fractions) {
            if (f.getMonth().equalsIgnoreCase(month)) return idx;
            idx++;
        }

        return -1;
    }

    private Fraction createFraction(String month, Double value, Profile profile) {
        return Fraction.Builder.newInstance()
                .month(month)
                .value(value)
                .profile(profile)
                .build();
    }

}
