package com.transformservice.repository;

import com.transformservice.domain.entity.Reading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReadingRepository extends JpaRepository<Reading, Long> {

    @Query("select r from Reading r " +
            "where r.meter.profile.id = :profileId " +
            "and r.meter.id = :meterId")
    List<Reading> findAllByProfileAndMeter(@Param("profileId") Long profileId,
                                           @Param("meterId") Long meterId);
}
