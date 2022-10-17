package com.energyconsumption.repository;

import com.energyconsumption.domain.entity.Fraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FractionRepository extends JpaRepository<Fraction, Long> {

    @Query("select f from Fraction f " +
            "where f.profile.id = :profileId")
    List<Fraction> findAllByProfileId(@Param("profileId") Long profileId);


    @Query("select f from Fraction f " +
            "where f.profile.id = :profileId " +
            "and f.id = :fractionId")
    Optional<Fraction> findAllByProfileIdAndFractionId(@Param("profileId") Long profileId,
                                                       @Param("fractionId") Long fractionId);

}
