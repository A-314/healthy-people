package com.emelyan.repositories;

import com.emelyan.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient,Long> {

    @Query("select p from Patient p where lower(p.person.name) like CONCAT('%',lower(:filter),'%') " +
            "or lower(p.person.surname) like CONCAT('%',lower(:filter),'%')" +
            "or lower(p.person.patronymic) like CONCAT('%',lower(:filter),'%')" +
            "or lower(p.person.phone) like CONCAT('%',lower(:filter),'%')" +
            "or lower(p.person.factAddress) like CONCAT('%',lower(:filter),'%')")
    List<Patient> findPatientsWithFilter(@Param("filter") String filter);

}