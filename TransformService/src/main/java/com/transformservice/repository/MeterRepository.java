package com.transformservice.repository;

import com.transformservice.domain.entity.Meter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MeterRepository extends JpaRepository<Meter, Long> {

    @Query("select m from Meter m " +
            "where m.profile.id = :profileId")
    List<Meter> findAllByProfileId(@Param("profileId") Long profileId);

    @Query("select m from Meter m " +
            "where m.profile.id = :profileId " +
            "and m.id = :meterId")
    Optional<Meter> findByProfileIdAndMeterId(@Param("profileId") Long profileId,
                                              @Param("meterId") Long meterId);

}
