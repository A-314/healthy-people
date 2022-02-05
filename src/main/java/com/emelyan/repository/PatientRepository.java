package com.emelyan.repository;

import com.emelyan.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PatientRepository extends JpaRepository <Patient,Long> {
}
