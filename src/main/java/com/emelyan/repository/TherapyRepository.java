package com.emelyan.repository;

import com.emelyan.model.Therapy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TherapyRepository extends JpaRepository<Therapy,Long> {
}
