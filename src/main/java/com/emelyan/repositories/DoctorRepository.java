package com.emelyan.repositories;

import com.emelyan.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    @Query(" select d from Doctor d where lower(d.person.name) like concat('%',lower(:filter),'%')" +
            "or lower(d.person.surname) like concat('%',lower(:filter),'%')")
    List<Doctor> findDoctorsWithFilter(String filter);
}
