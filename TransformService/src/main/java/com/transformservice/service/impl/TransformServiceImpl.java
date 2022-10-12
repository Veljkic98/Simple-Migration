package com.transformservice.service.impl;

import com.opencsv.bean.CsvToBeanBuilder;
import com.transformservice.domain.dto.MeterDto;
import com.transformservice.domain.dto.UploadMeterReadingDto;
import com.transformservice.domain.dto.UploadProfileDto;
import com.transformservice.domain.entity.Fraction;
import com.transformservice.domain.entity.Meter;
import com.transformservice.domain.entity.Profile;
import com.transformservice.domain.entity.Reading;
import com.transformservice.exception.DataNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class TransformServiceImpl {

    private final ProfileServiceImpl profileService;

    private final MeterServiceImpl meterService;

    private final FractionServiceImpl fractionServiceImpl;

    private final ReadingServiceImpl readingService;

    Logger log = LoggerFactory.getLogger(TransformServiceImpl.class);

    @Autowired
    public TransformServiceImpl(ProfileServiceImpl profileService,
                                MeterServiceImpl meterService,
                                FractionServiceImpl fractionServiceImpl,
                                ReadingServiceImpl readingService) {
        this.profileService = profileService;
        this.meterService = meterService;
        this.fractionServiceImpl = fractionServiceImpl;
        this.readingService = readingService;
    }

    /**
     * Read, parse and save Meters, Fractions and Profiles
     */
    public void parse(MultipartFile fileProfiles, MultipartFile fileMeterReadings) throws IOException {
        List<Profile> profiles = parseAndCreateProfiles(fileProfiles);

        Map<String, Profile> profilesMap = profiles.stream().collect(Collectors.toMap(Profile::getName, Function.identity()));
        List<Meter> meters = parseAndCreateMeters(profilesMap, fileMeterReadings);
        parseAndCreateFractions(profilesMap, fileProfiles);

        Map<String, Meter> metersMap = meters.stream().collect(Collectors.toMap(Meter::getMeterIdentifier, Function.identity()));
        parseAndCreateReadings(metersMap, fileMeterReadings);
    }

    private List<Profile> parseAndCreateProfiles(MultipartFile fileProfiles) throws IOException {
        List<UploadProfileDto> uploadMeterReadings = loadDistinctUploadProfiles(fileProfiles);
        List<Profile> profiles = new ArrayList<>();

        uploadMeterReadings.forEach(p -> profiles.add(profileService.create(Profile.Builder.newInstance()
                .name(p.getProfile())
                .build())));

        return profiles;
    }

    private List<Meter> parseAndCreateMeters(Map<String, Profile> profiles, MultipartFile fileMeterReadings) throws IOException {
        List<UploadMeterReadingDto> readingDtos = loadDistinctUploadMeterReadings(fileMeterReadings);
        List<Meter> meters = new ArrayList<>();

        readingDtos.forEach(mr -> {
            Profile profile;

            try {
                profile = profiles.get(mr.getProfile());
            } catch (NullPointerException e) {
                throw new DataNotFoundException(String.format("Profile with name: %s not found.", mr.getProfile()));
            }

            meters.add(meterService.create(profile.getId(), MeterDto.Builder.newInstance()
                    .meterIdentifier(mr.getMeterIdentifier())
                    .build()));
        });

        return meters;
    }

    private void parseAndCreateFractions(Map<String, Profile> profiles, MultipartFile fileProfiles) throws IOException {
        List<UploadProfileDto> profileDtos = loadUploadProfiles(fileProfiles);

        profileDtos.forEach( p -> {
            Profile profile;

            try {
                profile = profiles.get(p.getProfile());
            } catch (NullPointerException e) {
                throw new DataNotFoundException(String.format("Profile with name: %s not found.", p.getProfile()));
            }

            fractionServiceImpl.create(Fraction.Builder.newInstance()
                    .month(p.getMonth())
                    .value(p.getFraction())
                    .profile(profile)
                    .build());
        });
    }

    private void parseAndCreateReadings(Map<String, Meter> meters, MultipartFile file) throws IOException {
        List<UploadMeterReadingDto> readingDtos = loadUploadMeterReadings(file);

        readingDtos.forEach(mr -> {
            readingService.create(Reading.Builder.newInstance()
                    .meter(meters.get(mr.getMeterIdentifier()))
                            .month(mr.getMonth())
                            .value(mr.getMeterReading())
                    .build());
        });
    }

    private List<UploadProfileDto> loadDistinctUploadProfiles(MultipartFile file) throws IOException {
        List<UploadProfileDto> uploadMeterReadings = loadUploadProfiles(file);

        return uploadMeterReadings.stream()
                .filter(distinctByKey(UploadProfileDto::getProfile))
                .collect(Collectors.toList());
    }

    private List<UploadProfileDto> loadUploadProfiles(MultipartFile file) throws IOException {
        BufferedReader fileReader = new BufferedReader(new
                InputStreamReader(file.getInputStream(), "UTF-8"));
        return new CsvToBeanBuilder(fileReader)
                .withType(UploadProfileDto.class)
                .build()
                .parse();
    }

    private List<UploadMeterReadingDto> loadDistinctUploadMeterReadings(MultipartFile file) throws IOException {
        List<UploadMeterReadingDto> uploadMeterReadings = loadUploadMeterReadings(file);

        return uploadMeterReadings.stream()
                .filter(distinctByKey(UploadMeterReadingDto::getProfile))
                .collect(Collectors.toList());
    }

    private List<UploadMeterReadingDto> loadUploadMeterReadings(MultipartFile file) throws IOException {
        BufferedReader fileReader = new BufferedReader(new
                InputStreamReader(file.getInputStream(), "UTF-8"));

        return new CsvToBeanBuilder(fileReader)
                .withType(UploadMeterReadingDto.class)
                .build()
                .parse();
    }

    private <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

}
