package com.transformservice.service.impl;

import com.transformservice.domain.dto.ReadingsDto;
import com.transformservice.domain.entity.Fraction;
import com.transformservice.domain.entity.Meter;
import com.transformservice.domain.entity.Reading;
import com.transformservice.exception.DataMissingException;
import com.transformservice.exception.InvalidDataException;
import com.transformservice.repository.ReadingRepository;
import com.transformservice.service.FractionService;
import com.transformservice.service.MeterService;
import com.transformservice.service.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.transformservice.util.ApplicationConstants.createMapOfMonths;

@Service
public class ReadingServiceImpl implements ReadingService {

    private final ReadingRepository readingRepository;

    private final MeterService meterService;

    private final FractionService fractionService;

    @Autowired
    public ReadingServiceImpl(ReadingRepository readingRepository,
                              MeterService meterService,
                              FractionService fractionService) {
        this.readingRepository = readingRepository;
        this.meterService = meterService;
        this.fractionService = fractionService;
    }

    @Override
    public List<Reading> getAll(Long profileId, Long meterId) {
        return readingRepository.findAllByProfileAndMeter(profileId, meterId);
    }

    @Override
    public List<Reading> create(Long profileId, Long meterId, ReadingsDto readingsDto) {
        validateReadingFields(readingsDto);

        Meter meter = meterService.getById(profileId, meterId);

        List<Reading> readings = createReadings(meter, readingsDto);

        validateReadingsIncreasingByMonths(meterId, readings);
        validateReadingsProportionalityWithFractions(profileId, readings);

        return readingRepository.saveAll(readings);
    }

    @Override
    public List<Reading> update(Long profileId, Long meterId, ReadingsDto readingsDto) {
        validateReadingFields(readingsDto);

        List<Reading> readings = getAll(profileId, meterId);

        validateReadingsIncreasingByMonths(meterId, readings);
        validateReadingsProportionalityWithFractions(profileId, readings);

        updateReadings(readings, readingsDto);

        return readingRepository.saveAll(readings);
    }

    @Override
    public void delete(Long profileId, Long meterId) {
        List<Reading> readings = getAll(profileId, meterId);

        readingRepository.deleteAll(readings);
    }

    /**
     * @param meterId is ID of Meter
     * @param readings is list of Readings by months
     */
    private void validateReadingsIncreasingByMonths(Long meterId, List<Reading> readings) {
        if (!isReadingsIncreasingByMonths(readings)) {
            throw new InvalidDataException(
                    String.format("Meter readings are not increasing by months for meter with id: %s.", meterId));
        }
    }

    /**
     * @param profileId is ID of Profile
     * @param readings is List of Readings by months
     */
    private void validateReadingsProportionalityWithFractions(Long profileId, List<Reading> readings) {
        if (!isMeterReadingProportionalWithFractions(profileId, readings)) {
            throw new InvalidDataException(
                    String.format("Reading for profile with id %s is not proportional with fraction.", profileId));
        }
    }

    private void validateReadingFields(ReadingsDto readingsDto) {
        if (readingsDto.isAnyFieldNull()) {
            throw new DataMissingException("Readings by all 12 months must not be null.");
        }
    }

    private boolean isMeterReadingProportionalWithFractions(Long profileId, List<Reading> readings) {
        List<Fraction> fractions = fractionService.getAll(profileId);

        double desirableMeterReading = 0;
        for (int i = 0; i < 12; i++) {
            Fraction f = fractions.get(i);
            Reading r = readings.get(i);

            desirableMeterReading += (240 * f.getValue());
            if (r.getValue() > desirableMeterReading * 1.25 || r.getValue() < desirableMeterReading * 0.75) {
                return false;
            }
        }

        return true;
    }

    private boolean isReadingsIncreasingByMonths(List<Reading> readings) {
        int meterReading = 0;

        for (Reading p : readings) {
            if (p.getValue() < meterReading) return false;
            meterReading = p.getValue();
        }

        return true;
    }

    private List<Reading> createReadings(Meter meter, ReadingsDto readingsDto) {
        List<Reading> readings = new ArrayList<>();

        Map<Integer, String> months = createMapOfMonths();

        readings.add(createReading(months.get(0), meter, readingsDto.getJanReading()));
        readings.add(createReading(months.get(1), meter, readingsDto.getFebReading()));
        readings.add(createReading(months.get(2), meter, readingsDto.getMarReading()));
        readings.add(createReading(months.get(3), meter, readingsDto.getAprReading()));
        readings.add(createReading(months.get(4), meter, readingsDto.getMayReading()));
        readings.add(createReading(months.get(5), meter, readingsDto.getJunReading()));
        readings.add(createReading(months.get(6), meter, readingsDto.getJulReading()));
        readings.add(createReading(months.get(7), meter, readingsDto.getAvgReading()));
        readings.add(createReading(months.get(8), meter, readingsDto.getSepReading()));
        readings.add(createReading(months.get(9), meter, readingsDto.getOctReading()));
        readings.add(createReading(months.get(10), meter, readingsDto.getNovReading()));
        readings.add(createReading(months.get(11), meter, readingsDto.getDecReading()));

        return readings;
    }

    private void updateReadings(List<Reading> readings, ReadingsDto readingsDto) {
        readings.get(findIdxOfFractionByMonth("JAN", readings)).setValue(readingsDto.getJanReading());
        readings.get(findIdxOfFractionByMonth("FEB", readings)).setValue(readingsDto.getFebReading());
        readings.get(findIdxOfFractionByMonth("MAR", readings)).setValue(readingsDto.getMarReading());
        readings.get(findIdxOfFractionByMonth("APR", readings)).setValue(readingsDto.getAprReading());
        readings.get(findIdxOfFractionByMonth("MAY", readings)).setValue(readingsDto.getMayReading());
        readings.get(findIdxOfFractionByMonth("JUN", readings)).setValue(readingsDto.getJunReading());
        readings.get(findIdxOfFractionByMonth("JUL", readings)).setValue(readingsDto.getJulReading());
        readings.get(findIdxOfFractionByMonth("AVG", readings)).setValue(readingsDto.getAvgReading());
        readings.get(findIdxOfFractionByMonth("SEP", readings)).setValue(readingsDto.getSepReading());
        readings.get(findIdxOfFractionByMonth("OCT", readings)).setValue(readingsDto.getOctReading());
        readings.get(findIdxOfFractionByMonth("NOV", readings)).setValue(readingsDto.getNovReading());
        readings.get(findIdxOfFractionByMonth("DEC", readings)).setValue(readingsDto.getDecReading());
    }

    private Reading createReading(String month, Meter meter, Integer readingValue) {
        return Reading.Builder.newInstance()
                .month(month)
                .meter(meter)
                .value(readingValue)
                .build();
    }

    private int findIdxOfFractionByMonth(String month, List<Reading> readings) {
        int idx = 0;
        for (Reading r : readings) {
            if (r.getMonth().equalsIgnoreCase(month)) return idx;
            idx++;
        }

        return -1;
    }

}
