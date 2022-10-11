package com.transformservice.service;

import com.opencsv.bean.CsvToBeanBuilder;
import com.transformservice.domain.dto.UploadMeterReadingDto;
import com.transformservice.domain.dto.UploadProfileDto;
import com.transformservice.domain.entity.Fraction;
import com.transformservice.domain.entity.Meter;
import com.transformservice.domain.entity.Profile;
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

    Logger log = LoggerFactory.getLogger(TransformService.class);

    @Autowired
    public TransformService(ProfileService profileService, MeterService meterService, FractionService fractionService) {
        this.profileService = profileService;
        this.meterService = meterService;
        this.fractionService = fractionService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void proceed() throws IOException {
        List<Profile> profiles = parseAndCreateProfiles();
        Map<String, Profile> profilesMap = profiles.stream().collect(Collectors.toMap(Profile::getName, Function.identity()));
        parseAndSaveMeters(profilesMap);
        parseAndSaveFractions(profilesMap);
    }

    private List<Profile> parseAndCreateProfiles() throws IOException {
        List<UploadMeterReadingDto> uploadMeterReadings = loadDistinctUploadProfiles();
        List<Profile> profiles = new ArrayList<>();

        uploadMeterReadings.forEach(p -> {
            profiles.add(profileService.create(Profile.Builder.newInstance()
                    .name(p.getProfile())
                    .build()));
        });

        return profiles;
    }

    private void parseAndSaveMeters(Map<String, Profile> profiles) throws IOException {
        List<UploadMeterReadingDto> readingDtos = loadUploadMeterReadings();

        readingDtos.forEach(mr -> {
            meterService.create(Meter.Builder.newInstance()
                    .meterIdentifier(mr.getMeterIdentifier())
                    .profile(profiles.get(mr.getProfile()))
                    .build());
        });
    }

    private void parseAndSaveFractions(Map<String, Profile> profilesMap) throws IOException {
        List<UploadProfileDto> profileDtos = loadUploadProfiles();

        profileDtos.forEach( p -> {
            fractionService.create(Fraction.Builder.newInstance()
                    .month(p.getMonth())
                    .value(p.getFraction())
                    .profile(profilesMap.get(p.getProfile()))
                    .build());
        });
    }

    private List<UploadMeterReadingDto> loadDistinctUploadProfiles() throws IOException {
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

    private List<UploadProfileDto> loadUploadProfiles() throws IOException {
        return new CsvToBeanBuilder(new FileReader(csvFilePath + profilesFileName))
                .withType(UploadProfileDto.class)
                .build()
                .parse();
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

}
