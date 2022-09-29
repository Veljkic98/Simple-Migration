package com.dummydata.service;

import com.dummydata.model.Profile;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
     * Method to create profiles. For every profile will be created
     * 12 records, because of 12 months per year.
     * All data will be saved locally into .csv file
     */
    @EventListener(ApplicationReadyEvent.class)
    public void writeToCSV() throws Exception {
        List<Profile> profiles = createProfiles();

        List<Profile> profilesMixed = new ArrayList<>();
        while (!profiles.isEmpty()) {
            int profileIdx = random.nextInt(0, profiles.size());
            profilesMixed.add(profiles.remove(profileIdx));
        }

        saveToCSV(profilesMixed, "profiles");
    }

    private void saveToCSV(List<Profile> profiles, String fileName) throws Exception {
            Writer writer = new FileWriter(csvFilePath + fileName + ".csv");
            StatefulBeanToCsv<Profile> csvWriter = new StatefulBeanToCsvBuilder<Profile>(writer)
                    .withSeparator(',')
                    .withApplyQuotesToAll(false)
                    .build();
            csvWriter.write(profiles);
            writer.close();
    }

    private List<Profile> createProfiles() {
        List<Profile> profiles =new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_PROFILES; i++) {
            String profile = fakeValuesService.regexify("[A-E]{2}");
            for (int j = 0; j < 12; j++) {
                profiles.add(Profile.Builder.newInstance()
                        .setFraction(ARRAY_OF_FRACTIONS[j])
                        .setMonth(ARRAY_OF_MONTHS[j])
                        .setProfile(profile)
                        .build());
            }
        }
        return profiles;
    }

}
