package com.transformservice.service.impl;

import com.transformservice.domain.dto.MeterDto;
import com.transformservice.domain.entity.Meter;
import com.transformservice.domain.entity.Profile;
import com.transformservice.exception.DataNotFoundException;
import com.transformservice.repository.MeterRepository;
import com.transformservice.service.MeterService;
import com.transformservice.service.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MeterServiceImpl implements MeterService {

    private final MeterRepository meterRepository;

    private final ProfileService profileService;

    Logger log = LoggerFactory.getLogger(MeterServiceImpl.class);

    @Autowired
    public MeterServiceImpl(MeterRepository meterRepository, ProfileService profileService) {
        this.meterRepository = meterRepository;
        this.profileService = profileService;
    }

    @Override
    public List<Meter> getAllByProfile(Long profileId) {
        List<Meter> meters = meterRepository.findAllByProfileId(profileId);

        if (meters.isEmpty()) {
            log.warn("Meters for Profile with id {} not found.", profileId);
            throw new DataNotFoundException(String.format("Profile with id %s not found.", profileId));
        }

        return meters;
    }

    @Override
    public Meter getById(Long profileId, Long meterId) {
        Optional<Meter> meter = meterRepository.findByProfileIdAndMeterId(profileId, meterId);

        if (meter.isEmpty()) {
            log.warn("Meter with profile ID {} and meter ID {} not found.", profileId, meterId);
            throw new DataNotFoundException(
                    String.format("Meter with profile id %s and meter id %s not found", profileId, meterId));
        }

        return meter.get();
    }

    @Override
    public Meter create(Long profileId, MeterDto meterDto) {
        Profile profile = profileService.getById(profileId);

        return meterRepository.save(Meter.Builder.newInstance()
                .meterIdentifier(meterDto.getMeterIdentifier())
                .profile(profile)
                .build());
    }

    @Override
    public Meter update(Long profileId, Long meterId, MeterDto meterDto) {
        Meter meter = getById(profileId, meterId);

        meter.setMeterIdentifier(meterDto.getMeterIdentifier());

        return meterRepository.save(meter);
    }

    @Override
    @Transactional
    public void delete(Long profileId, Long meterId) {
        Meter meter = getById(profileId, meterId);

        meterRepository.delete(meter);
    }

}
