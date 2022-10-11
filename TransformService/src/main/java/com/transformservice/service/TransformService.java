package com.transformservice.service;

import com.opencsv.bean.CsvToBeanBuilder;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class TransformService {

    @Value("${csv.filePath}")
    private String csvFilePath;

    @Value("${meterReading.fileName}")
    private String meterReadingsFileName;

    @Value("${profile.fileName}")
    private String profilesFileName;

    private final ProfileService profileService;

    private final MeterService meterService;

    private final FractionService fractionService;

    private final ReadingService readingService;

    Logger log = LoggerFactory.getLogger(TransformService.class);

    @Autowired
    public TransformService(ProfileService profileService,
                            MeterService meterService,
                            FractionService fractionService,
                            ReadingService readingService) {
        this.profileService = profileService;
        this.meterService = meterService;
        this.fractionService = fractionService;
        this.readingService = readingService;
    }

    /**
     * Read, parse and save Meters, Fractions and Profiles
     */
    @EventListener(ApplicationReadyEvent.class)
    public void proceed() throws IOException {
        List<Profile> profiles = parseAndCreateProfiles();

        Map<String, Profile> profilesMap = profiles.stream().collect(Collectors.toMap(Profile::getName, Function.identity()));
        List<Meter> meters = parseAndCreateMeters(profilesMap);
        parseAndCreateFractions(profilesMap);

        Map<String, Meter> metersMap = meters.stream().collect(Collectors.toMap(Meter::getMeterIdentifier, Function.identity()));
        parseAndCreateReadings(metersMap);
    }

    private List<Profile> parseAndCreateProfiles() throws IOException {
        List<UploadProfileDto> uploadMeterReadings = loadDistinctUploadProfiles();
        List<Profile> profiles = new ArrayList<>();

        uploadMeterReadings.forEach(p -> profiles.add(profileService.create(Profile.Builder.newInstance()
                .name(p.getProfile())
                .build())));

        return profiles;
    }

    private List<Meter> parseAndCreateMeters(Map<String, Profile> profiles) throws IOException {
        List<UploadMeterReadingDto> readingDtos = loadDistinctUploadMeterReadings();
        List<Meter> meters = new ArrayList<>();

        readingDtos.forEach(mr -> {
            Profile profile;

            try {
                profile = profiles.get(mr.getProfile());
            } catch (NullPointerException e) {
                throw new DataNotFoundException(String.format("Profile with name: %s not found.", mr.getProfile()));
            }

            meters.add(meterService.create(Meter.Builder.newInstance()
                    .meterIdentifier(mr.getMeterIdentifier())
                    .profile(profile)
                    .build()));
        });

        return meters;
    }

    private void parseAndCreateFractions(Map<String, Profile> profiles) throws IOException {
        List<UploadProfileDto> profileDtos = loadUploadProfiles();

        profileDtos.forEach( p -> {
            Profile profile;

            try {
                profile = profiles.get(p.getProfile());
            } catch (NullPointerException e) {
                throw new DataNotFoundException(String.format("Profile with name: %s not found.", p.getProfile()));
            }

            fractionService.create(Fraction.Builder.newInstance()
                    .month(p.getMonth())
                    .value(p.getFraction())
                    .profile(profile)
                    .build());
        });
    }

    private void parseAndCreateReadings(Map<String, Meter> meters) throws IOException {
        List<UploadMeterReadingDto> readingDtos = loadUploadMeterReadings();

        readingDtos.forEach(mr -> {
            readingService.create(Reading.Builder.newInstance()
                    .meter(meters.get(mr.getMeterIdentifier()))
                            .month(mr.getMonth())
                            .value(mr.getMeterReading())
                    .build());
        });
    }

    private List<UploadProfileDto> loadDistinctUploadProfiles() throws IOException {
        List<UploadProfileDto> uploadMeterReadings = loadUploadProfiles();

        return uploadMeterReadings.stream()
                .filter(distinctByKey(UploadProfileDto::getProfile))
                .collect(Collectors.toList());
    }

    private List<UploadProfileDto> loadUploadProfiles() throws IOException {
        return new CsvToBeanBuilder(new FileReader(csvFilePath + profilesFileName))
                .withType(UploadProfileDto.class)
                .build()
                .parse();
    }

    private List<UploadMeterReadingDto> loadDistinctUploadMeterReadings() throws IOException {
        List<UploadMeterReadingDto> uploadMeterReadings = loadUploadMeterReadings();

        return uploadMeterReadings.stream()
                .filter(distinctByKey(UploadMeterReadingDto::getProfile))
                .collect(Collectors.toList());
    }

    private List<UploadMeterReadingDto> loadUploadMeterReadings() throws IOException {
        return new CsvToBeanBuilder(new FileReader(csvFilePath + meterReadingsFileName))
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
