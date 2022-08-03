package com.emelyan.repositories;

import com.emelyan.models.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface RecordRepository extends JpaRepository<Record,Integer> {

   @Query(value = "SELECT r from Record r where r.date between :startDate and :endDate ORDER BY r.interval.startTime, r.date ")
   ArrayList<Record> findAllByReception_dateIsBetween(@Param("startDate") Date startDate, @Param("endDate")Date endDate);

}
