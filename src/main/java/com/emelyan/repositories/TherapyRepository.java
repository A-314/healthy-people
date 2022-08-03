package com.emelyan.repositories;


import com.emelyan.models.Therapy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Date;

public interface TherapyRepository extends JpaRepository<Therapy, Long> {

    @Query("select concat(t.doctor.person.surname, ' ', t.doctor.person.name), count(t.patient) as patient  from Therapy t group by concat(t.doctor.person.surname, ' ', t.doctor.person.name)")
    List<String[]> getAll();

    @Query("select t.record.date, count(t.id) from Therapy t where t.record.date between :startDate and :endDate Group by t.record.date order by t.record.date ")
    List<String[]> getNumberOfPatientsByDay(@Param("startDate")Date startDate, @Param("endDate")Date endDate);
}
