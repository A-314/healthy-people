package com.emelyan.repositories;

import com.emelyan.models.Therapy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TherapyRepository extends JpaRepository<Therapy,Long> {
}
