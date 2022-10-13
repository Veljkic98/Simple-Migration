package com.transformservice.repository.impl;

import com.opencsv.bean.CsvToBeanBuilder;
import com.transformservice.domain.dto.*;
import com.transformservice.domain.entity.Meter;
import com.transformservice.domain.entity.Profile;
import com.transformservice.exception.InvalidDataException;
import com.transformservice.service.FractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class TransformServiceImpl {

    private final ProfileServiceImpl profileService;

    private final MeterServiceImpl meterService;

    private final FractionService fractionService;

    private final ReadingServiceImpl readingService;

    @Autowired
    public TransformServiceImpl(ProfileServiceImpl profileService,
                                MeterServiceImpl meterService,
                                FractionService fractionService,
                                ReadingServiceImpl readingService) {
        this.profileService = profileService;
        this.meterService = meterService;
        this.fractionService = fractionService;
        this.readingService = readingService;
    }

    /**
     * Read, parse and save Meters, Fractions and Profiles.
     *
     * @param fileProfiles
     * @param fileMeterReadings
     * @throws IOException
     */
    @Transactional
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

        uploadMeterReadings.forEach(p -> profiles.add(profileService.create(ProfileDto.Builder.newInstance()
                .name(p.getProfile())
                .build())));

        return profiles;
    }

    private List<Meter> parseAndCreateMeters(Map<String, Profile> profiles, MultipartFile fileMeterReadings) throws IOException {
        List<UploadMeterReadingDto> readingDtos = loadDistinctUploadMeterReadings(fileMeterReadings);
        List<Meter> meters = new ArrayList<>();

        readingDtos.forEach(mr -> {
            Profile profile = profiles.get(mr.getProfile());

            meters.add(meterService.create(profile.getId(), MeterDto.Builder.newInstance()
                    .meterIdentifier(mr.getMeterIdentifier())
                    .build()));
        });

        return meters;
    }

    private void parseAndCreateFractions(Map<String, Profile> profiles, MultipartFile fileProfiles) throws IOException {
        List<UploadProfileDto> profileDtos = loadUploadProfiles(fileProfiles);

        for (Map.Entry<String, Profile> profile : profiles.entrySet()) {
            fractionService.create(profile.getValue().getId(),
                    createFractionsForProfile(profile, profileDtos));
        }
    }

    private FractionsDto createFractionsForProfile(Map.Entry<String, Profile> profile, List<UploadProfileDto> profileDtos) {
        FractionsDto fractionsDto = new FractionsDto();

        profileDtos.forEach(p -> {
            if (p.getProfile().equalsIgnoreCase(profile.getValue().getName())) {
                attachFractionByMonth(fractionsDto, p);
            }
        });

        return fractionsDto;
    }

    private void attachFractionByMonth(FractionsDto fractionsDto, UploadProfileDto uploadProfileDto) {
        switch (uploadProfileDto.getMonth().toUpperCase()) {
            case "JAN" -> fractionsDto.setJanFraction(uploadProfileDto.getFraction());
            case "FEB" -> fractionsDto.setFebFraction(uploadProfileDto.getFraction());
            case "MAR" -> fractionsDto.setMarFraction(uploadProfileDto.getFraction());
            case "APR" -> fractionsDto.setAprFraction(uploadProfileDto.getFraction());
            case "MAY" -> fractionsDto.setMayFraction(uploadProfileDto.getFraction());
            case "JUN" -> fractionsDto.setJunFraction(uploadProfileDto.getFraction());
            case "JUL" -> fractionsDto.setJulFraction(uploadProfileDto.getFraction());
            case "AVG" -> fractionsDto.setAvgFraction(uploadProfileDto.getFraction());
            case "SEP" -> fractionsDto.setSepFraction(uploadProfileDto.getFraction());
            case "OCT" -> fractionsDto.setOctFraction(uploadProfileDto.getFraction());
            case "NOV" -> fractionsDto.setNovFraction(uploadProfileDto.getFraction());
            case "DEC" -> fractionsDto.setDecFraction(uploadProfileDto.getFraction());
            default -> throw new InvalidDataException(
                    String.format("Month '%s' for Fractions is not valid.", uploadProfileDto.getMonth().toUpperCase()));
        }
    }

    private void parseAndCreateReadings(Map<String, Meter> meters, MultipartFile file) throws IOException {
        List<UploadMeterReadingDto> readingDtos = loadUploadMeterReadings(file);

        for (Map.Entry<String, Meter> meter : meters.entrySet()) {
            readingService.create(meter.getValue().getProfile().getId(),
                    meter.getValue().getId(),
                    createReadingsForMeter(readingDtos, meter.getKey()));
        }
    }

    private ReadingsDto createReadingsForMeter(List<UploadMeterReadingDto> readingDtos, String meterIdentifier) {
        ReadingsDto readingsDto = new ReadingsDto();

        readingDtos.forEach(mr -> {
            if (mr.getMeterIdentifier().equalsIgnoreCase(meterIdentifier)) {
                attachReadingByMonth(readingsDto, mr);
            }
        });

        return readingsDto;
    }

    private void attachReadingByMonth(ReadingsDto readingsDto, UploadMeterReadingDto uploadMeterReadingDto) {
        switch (uploadMeterReadingDto.getMonth().toUpperCase()) {
            case "JAN" -> readingsDto.setJanReading(uploadMeterReadingDto.getMeterReading());
            case "FEB" -> readingsDto.setFebReading(uploadMeterReadingDto.getMeterReading());
            case "MAR" -> readingsDto.setMarReading(uploadMeterReadingDto.getMeterReading());
            case "APR" -> readingsDto.setAprReading(uploadMeterReadingDto.getMeterReading());
            case "MAY" -> readingsDto.setMayReading(uploadMeterReadingDto.getMeterReading());
            case "JUN" -> readingsDto.setJunReading(uploadMeterReadingDto.getMeterReading());
            case "JUL" -> readingsDto.setJulReading(uploadMeterReadingDto.getMeterReading());
            case "AVG" -> readingsDto.setAvgReading(uploadMeterReadingDto.getMeterReading());
            case "SEP" -> readingsDto.setSepReading(uploadMeterReadingDto.getMeterReading());
            case "OCT" -> readingsDto.setOctReading(uploadMeterReadingDto.getMeterReading());
            case "NOV" -> readingsDto.setNovReading(uploadMeterReadingDto.getMeterReading());
            case "DEC" -> readingsDto.setDecReading(uploadMeterReadingDto.getMeterReading());
            default -> throw new InvalidDataException(
                    String.format("Month '%s' for Reading is not valid.", uploadMeterReadingDto.getMonth().toUpperCase()));
        }
    }

    private List<UploadProfileDto> loadDistinctUploadProfiles(MultipartFile file) throws IOException {
        List<UploadProfileDto> uploadMeterReadings = loadUploadProfiles(file);

        return uploadMeterReadings.stream()
                .filter(distinctByKey(UploadProfileDto::getProfile))
                .collect(Collectors.toList());
    }

    private List<UploadProfileDto> loadUploadProfiles(MultipartFile file) throws IOException {
        BufferedReader fileReader = new BufferedReader(new
                InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
        return new CsvToBeanBuilder<UploadProfileDto>(fileReader)
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
                InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));

        return new CsvToBeanBuilder<UploadMeterReadingDto>(fileReader)
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
