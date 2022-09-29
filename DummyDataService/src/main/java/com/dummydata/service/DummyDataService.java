package com.dummydata.service;

import com.dummydata.model.MeterReading;
import com.dummydata.model.Profile;
import com.github.javafaker.service.FakeValuesService;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.dummydata.util.ApplicationConstants.*;

@Service
public class DummyDataService {

    private final FakeValuesService fakeValuesService;

    private final Random random = new Random();

    @Value("${csv.filePath}")
    private String csvFilePath;

    @Autowired
    public DummyDataService(FakeValuesService fakeValuesService) {
        this.fakeValuesService = fakeValuesService;
    }

    /**
     * Method to create profiles and meter reading.
     * For every profile and meter reading will be created
     * 12 records, because of 12 months per year.
     * All data will be saved locally into .csv file
     */
    @EventListener(ApplicationReadyEvent.class)
    public void writeToCSV() throws Exception {
        String[] profileNames = new String[3];
        for (int i = 0; i < NUMBER_OF_PROFILES; i++) {
            profileNames[i] = fakeValuesService.regexify("[A-E]{2}");
        }
        writeProfilesToCSV(createProfiles(profileNames));
        writeMeterReadingsToCSV(createMeterReadings(profileNames));
    }

    private void writeProfilesToCSV(List<Profile> profiles) throws Exception {
        List<Profile> profilesMixed = new ArrayList<>();
        while (!profiles.isEmpty()) {
            int profileIdx = random.nextInt(0, profiles.size());
            profilesMixed.add(profiles.remove(profileIdx));
        }
        saveProfilesToCSV(profilesMixed, "profiles");
    }

    public void writeMeterReadingsToCSV(List<MeterReading> meterReadings) throws Exception {
        List<MeterReading> meterReadingsMixed = new ArrayList<>();
        while (!meterReadings.isEmpty()) {
            int meterReadingIdx = random.nextInt(0, meterReadings.size());
            meterReadingsMixed.add(meterReadings.remove(meterReadingIdx));
        }
        saveMeterReadingsToCSV(meterReadingsMixed, "meterReadings");
    }

    private void saveProfilesToCSV(List<Profile> profiles, String fileName) throws Exception {
        Writer writer = new FileWriter(csvFilePath + fileName + ".csv");
        StatefulBeanToCsv<Profile> csvWriter = new StatefulBeanToCsvBuilder<Profile>(writer)
                .withSeparator(',')
                .withApplyQuotesToAll(false)
                .build();
        csvWriter.write(profiles);
        writer.close();
    }

    private void saveMeterReadingsToCSV(List<MeterReading> meterReadings, String fileName) throws Exception {
        Writer writer = new FileWriter(csvFilePath + fileName + ".csv");
        StatefulBeanToCsv<MeterReading> csvWriter = new StatefulBeanToCsvBuilder<MeterReading>(writer)
                .withSeparator(',')
                .withApplyQuotesToAll(false)
                .build();
        csvWriter.write(meterReadings);
        writer.close();
    }

    private List<Profile> createProfiles(String[] profileNames) {
        List<Profile> profiles =new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_PROFILES; i++) {
            for (int j = 0; j < 12; j++) {
                profiles.add(Profile.Builder.newInstance()
                        .setFraction(ARRAY_OF_FRACTIONS[j])
                        .setMonth(ARRAY_OF_MONTHS[j])
                        .setProfile(profileNames[i])
                        .build());
            }
        }
        return profiles;
    }

    private List<MeterReading> createMeterReadings(String[] profileNames) {
        List<MeterReading> meterReadings = new ArrayList<>();
        int meter;
        for (int i = 0; i < NUMBER_OF_PROFILES; i++) {
            meter = random.nextInt(10);
            for (int j = 0; j < 12; j++) {
                meterReadings.add(MeterReading.Builder.newInstance()
                        .setMeterID(fakeValuesService.regexify("[0-4]{2}"))
                        .setMonth(ARRAY_OF_MONTHS[j])
                        .setProfile(profileNames[i])
                        .setMeter((meter + random.nextInt(5)) + "")
                        .build());
            }
        }
        return meterReadings;
    }

}
