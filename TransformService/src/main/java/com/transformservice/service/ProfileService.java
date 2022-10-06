package com.transformservice.service;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import com.transformservice.domain.entity.Profile;
import com.transformservice.exception.DataNotFoundException;
import com.transformservice.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static com.transformservice.util.ApplicationConstants.createMapOfMonths;

@Service
public class ProfileService {

    @Value("${csv.filePath}")
    private String csvFilePath;

    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    /**
     * This method read from meter reading and profile files
     * and create one object derived from these. Validate newly
     * created objects and write them to database.
     *
     * @throws Exception
     */
    public void proceed() throws IOException, CsvValidationException {
        HashSet<String> meterIds = readMeterIds("meterReadings.csv");

        for (String mid : meterIds) {
            List<Profile> profiles = findData(mid);
            attachFractions(profiles);
            if (isValid(profiles)) {
                profileRepository.saveAll(profiles);
            }
        }
    }

    /*
     * Method to check are all 12 profiles valid
     * to be saved to DB.
     */
    private boolean isValid(List<Profile> profiles) {
        sortProfiles(profiles);

        boolean canBeSaved = isAllFieldsNotNull(profiles) &&
                isSumOfAllFractionsEqualsOne(profiles) &&
                isMeterReadingIncreasingByMonths(profiles);

        removeDisproportionateMeterReadings(profiles);

        return canBeSaved;
    }

    /*
     * Method to sort profiles by months.
     */
    private void sortProfiles(List<Profile> profiles) {
        Map<String, Integer> months = createMapOfMonths();

        Comparator<Profile> customComparator = (left, right) -> {
            Integer leftMonthNum = months.get(left.getMonth());
            Integer rightMonthNum = months.get(right.getMonth());

            return leftMonthNum < rightMonthNum ? -1 : 1;
        };

        profiles.sort(customComparator);
    }

    /*
     * Method to remove disproportionate meter readings.
     * If meter reading is bigger or smaller than
     * 25% deviation of desirable meter reading than
     * it will be removed.
     */
    private void removeDisproportionateMeterReadings(List<Profile> profiles) {
        ListIterator<Profile> iter = profiles.listIterator();
        double desirableMeterReading = 0;
        while (iter.hasNext()) {
            Profile p = iter.next();
            desirableMeterReading += (240 * p.getFraction());
            if (p.getMeterReading() > desirableMeterReading * 1.25 || p.getMeterReading() < desirableMeterReading * 0.75) {
                iter.remove();
            }
        }
    }

    /*
     * Validation method to check is meter reading
     * increasing or stay same by every month.
     */
    private boolean isMeterReadingIncreasingByMonths(List<Profile> profiles) {
        int meterReading = 0;
        for (Profile p : profiles) {
            if (p.getMeterReading() < meterReading) return false;
            meterReading = p.getMeterReading();
        }

        return true;
    }

    private boolean isSumOfAllFractionsEqualsOne(List<Profile> profiles) {
        return profiles.stream().map(Profile::getFraction).reduce(0.0, (a, b) -> a + b) == 1;
    }

    private boolean isAllFieldsNotNull(List<Profile> profiles) {
        for (Profile p : profiles) {
            if (p.getMeterReading() == null || p.getMonth() == null || p.getFraction() == null || p.getName() == null) {
                return false;
            }
        }
        return true;
    }

    /*
     * Method to find fractions from file with profiles and fractions
     * and attach them to profiles.
     */
    private void attachFractions(List<Profile> profiles) throws IOException, CsvValidationException {
        FileReader filereader = new FileReader(csvFilePath + "profiles.csv");
        CSVReader csvReader = new CSVReaderBuilder(filereader)
                .withSkipLines(1)
                .build();

        String[] nextLine;
        while ((nextLine = csvReader.readNext()) != null) {
            if (nextLine[0].isBlank() || nextLine[1].isBlank() || nextLine[2].isBlank()) {
                throw new DataNotFoundException();
            }

            for (Profile p : profiles) {
                if (p.getMonth().equals(nextLine[1]) && p.getName().equals(nextLine[2])) {
                    p.setFraction(Double.parseDouble(nextLine[0]));
                }
            }
        }
    }

    private List<Profile> findData(String meterId) throws IOException, CsvValidationException {
        FileReader filereader = new FileReader(csvFilePath + "meterReadings.csv");
        CSVReader csvReader = new CSVReaderBuilder(filereader)
                .withSkipLines(1)
                .build();

        return createProfiles(csvReader, meterId);
    }

    private List<Profile> createProfiles(CSVReader csvReader, String meterId) throws IOException, CsvValidationException {
        List<Profile> profiles = new ArrayList<>();

        String[] nextLine;
        while ((nextLine = csvReader.readNext()) != null) {
            if (nextLine[0].isBlank() || nextLine[1].isBlank() || nextLine[2].isBlank() || nextLine[3].isBlank()) {
                throw new DataNotFoundException();
            }

            if (nextLine[1].equals(meterId)) {
                profiles.add(buildProfile(nextLine));
            }
        }

        return profiles;
    }

    private HashSet<String> readMeterIds(String fileName) throws IOException, CsvValidationException {
        FileReader filereader = new FileReader(csvFilePath + fileName);
        CSVReader csvReader = new CSVReaderBuilder(filereader)
                .withSkipLines(1)
                .build();

        HashSet<String> meterIds = new HashSet<>();

        String[] nextLine;
        while((nextLine = csvReader.readNext()) != null) {
            if (nextLine[0].isBlank()) {
                throw new DataNotFoundException();
            }

            meterIds.add(nextLine[1]);
        }

        return meterIds;
    }

    private Profile buildProfile(String[] row) {
        return Profile.Builder.newInstance()
                .setMeterReading(Integer.parseInt(row[0]))
                .setMonth(row[2])
                .setName(row[3])
                .build();
    }

}
