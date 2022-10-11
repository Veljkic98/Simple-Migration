package com.transformservice.repository;

import com.transformservice.domain.entity.Fraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FractionRepository extends JpaRepository<Fraction, Long> {
}
