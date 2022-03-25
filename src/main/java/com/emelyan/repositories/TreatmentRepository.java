package com.emelyan.repositories;

import com.emelyan.models.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  TreatmentRepository extends JpaRepository<Treatment, Long> {
}
