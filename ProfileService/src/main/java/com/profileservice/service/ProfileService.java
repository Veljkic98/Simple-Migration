package com.profileservice.service;

import com.profileservice.domain.dto.ConsumptionDto;
import com.profileservice.domain.dto.ProfileDto;
import com.profileservice.domain.entity.Profile;
import com.profileservice.exception.DataNotFoundException;
import com.profileservice.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.profileservice.util.ApplicationConstants.createMapOfMonths;

/**
 * @author veljkoplecas
 */
@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    /**
     * Method to find profile by id.
     *
     * @param profileId is ID of profile
     * @throws DataNotFoundException if Profile by profileId is not found.
     * @return ProfileDto
     */
    public ProfileDto getOne(Long profileId) {
        Optional<Profile> profile;

        if ((profile = profileRepository.findById(profileId)).isEmpty()) {
            throw new DataNotFoundException();
        }

        return ProfileDto.fromEntity(profile.get());
    }

    /**
     * Metod to find all 12 profiles by profile name.
     *
     * @param name is profile name
     * @return list of profiles
     */
    public List<ProfileDto> getAllByName(String name) {
        List<ProfileDto> profiles = profileRepository.findAllByName(name)
                .stream()
                .map(ProfileDto::fromEntity)
                .collect(Collectors.toList());

        sortProfiles(profiles);

        return profiles;
    }

    /**
     * Method to find consumption between 2 months.
     * If months are same that means consumption
     * only for that month.
     *
     * @param name
     * @param monthFrom
     * @param monthTo
     * @return
     */
    public ConsumptionDto getConsumptionForMonths(String name, String monthFrom, String monthTo) {
        List<ProfileDto> profiles = profileRepository.findAllByName(name)
                .stream()
                .map(ProfileDto::fromEntity)
                .collect(Collectors.toList());

        Optional<ProfileDto> profileOfMonthFrom = profiles
                .stream()
                .filter(p -> p.getMonth().equals(monthFrom.toUpperCase()))
                .findAny();

        if (profileOfMonthFrom.isEmpty()) {
            throw new DataNotFoundException();
        }

        if (monthFrom.equalsIgnoreCase(monthTo))
            return findConsumptionForOneMonth(profileOfMonthFrom.get(), profiles);

        Optional<ProfileDto> profileOfMonthTo = profiles
                .stream()
                .filter(p -> p.getMonth().equals(monthTo.toUpperCase()))
                .findAny();

        if (profileOfMonthTo.isEmpty()) {
            throw new DataNotFoundException();
        }

        return ConsumptionDto
                .Builder
                .newInstance()
                .monthFrom(monthFrom.toUpperCase())
                .monthTo(monthTo.toUpperCase())
                .consumption(profileOfMonthTo.get().getMeterReading() - profileOfMonthFrom.get().getMeterReading())
                .build();
    }

    private ConsumptionDto findConsumptionForOneMonth(ProfileDto profileDto, List<ProfileDto> profiles) {
        if (profileDto.getMonth().equalsIgnoreCase("JAN"))
            return ConsumptionDto.Builder.newInstance()
                    .monthFrom(profileDto.getMonth())
                    .monthTo(profileDto.getMonth())
                    .consumption(profileDto.getMeterReading())
                    .build();

        int previousMonthMeterReading = getPreviousMonthMeterReading(profileDto.getMonth(), profiles);

        return ConsumptionDto.Builder.newInstance()
                .monthFrom(profileDto.getMonth())
                .monthTo(profileDto.getMonth())
                .consumption(profileDto.getMeterReading() - previousMonthMeterReading)
                .build();
    }

    private Integer getPreviousMonthMeterReading(String month, List<ProfileDto> profiles) {
        ProfileDto previousProfile = new ProfileDto();
        for (ProfileDto p : profiles) {
            if (p.getMonth().equalsIgnoreCase(month)) {
                return previousProfile.getMeterReading();
            }
            previousProfile = p;
        }

        throw new DataNotFoundException();
    }

    private void sortProfiles(List<ProfileDto> profiles) {
        Map<String, Integer> months = createMapOfMonths();

        Comparator<ProfileDto> customComparator = (left, right) -> {
            Integer leftMonthNum = months.get(left.getMonth());
            Integer rightMonthNum = months.get(right.getMonth());

            return leftMonthNum < rightMonthNum ? -1 : 1;
        };

        profiles.sort(customComparator);
    }

}
